package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ControlForm;
import model.Upload;
import tools.Tools;

public class UploadCommand implements ICommand {

    public static final String FOLDER_ALBUM = "albumphoto/";
    AlbumMap mapalbum = new AlbumMap();

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap urlParams) {
        request.setAttribute(TITRE_PAGE, "Upload");
        request.setAttribute(NOM_PAGE, "Nouvelle photo");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        ArrayList<AlbumBean> list = mapalbum.getAllbyAttr("idUser", user.getIdUser());
        request.setAttribute("listAlbum", list);

        if (request.getMethod().equals("POST")) {
            return upload(request);
        } else {
            return new ActionFlow("photos/ajouter", false);
        }

    }

    synchronized public ActionFlow upload(HttpServletRequest request) {
        ControlForm form = new ControlForm(request);
        String title = form.check("titre", ControlForm.NON_VIDE, "Donnez un titre à votre photo.");
        String namealbum = form.check("album", ControlForm.ENTIER);
        String descr = form.check("description", ControlForm.NON_VIDE, "Une petite description?");
        if (form.getNbError() == 0) {
            Upload up = new Upload("file", new String[]{"jpg", "jpeg", "png"});
            AlbumBean album = (AlbumBean) mapalbum.getbyId(Integer.parseInt(namealbum));
            if (album != null) {
                namealbum = album.getNameAlbum();
                String nameCrypt = Tools.crypt(namealbum, Tools.SHA1, true).replace("/", "").replace("=", "");
                String path = Tools.appPath + File.separator + FOLDER_ALBUM + nameCrypt;
                File f = new File(path);
                f.mkdirs();
                int state = up.uploadFile(request, path);
                if (state == 0) {
                    f = new File(path + File.separator + up.getFileName());
                    try {
                        Image img = ImageIO.read(f);
                        PhotoBean photo = new PhotoBean();
                        photo.setTitle(title);
                        photo.setDescr(descr);
                        photo.setImg(up.getFileName());
                        photo.setHeight(img.getHeight(null));
                        photo.setWidth(img.getWidth(null));
                        photo.setKeyword(Tools.generate_KeyWord(photo.getDescr()));
                        photo.setIdAlbum(album.getIdAlbum());
                        PhotoMap maphoto = new PhotoMap();
                        if (maphoto.save(photo) == 1) {
                            form.clean();
                            form.setResult(ControlForm.RES_VALID, "Votre photo est maintenant enregistrée !");
                        } else {
                            form.setResult(ControlForm.RES_VALID, "L'upload ne s'est pas terminé correctement, une erreur serveur s'est produite.");
                        }
                    } catch (IOException ex) {
                        form.setResult(ControlForm.RES_ERROR, "L'upload ne s'est pas terminé correctement, une erreur serveur s'est produite.");
                        System.out.println("[ Erreur lecture image ] : " + ex.getMessage());
                    } catch (NullPointerException ex) {
                        f.delete();
                        form.setResult(ControlForm.RES_ERROR, "Votre fichier n'est pas une image.");
                        System.out.println("[ Erreur lecture image ] : " + ex.getMessage());
                    }
                } else {
                    String[] errorUpload = new String[]{
                        "Veuillez selectionner une photo à uploader.",
                        "Votre image doit faire 10Mo maximum.",
                        "L'upload ne s'est pas terminé correctement, une erreur serveur s'est produite.",
                        "L'upload ne s'est pas terminé correctement, une erreur serveur s'est produite."};
                    form.setResult(ControlForm.RES_ERROR, errorUpload[state - 1]);
                }
            } else {
                request.setAttribute(ErrorCommand.MESSAGE_ERROR, "L'album '" + namealbum + "' n'existe pas.");
                return new ActionFlow("error", false);
            }
        }
        form.close();
        return new ActionFlow("photos/ajouter", false);
    }
}
