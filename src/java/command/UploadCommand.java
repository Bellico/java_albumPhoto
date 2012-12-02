package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bean.AlbumBean;
import bean.PhotoBean;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import model.ControlForm;
import model.Upload;
import tools.Tools;

public class UploadCommand extends Command {

    public static final String FOLDER_ALBUM = "albumphoto/";

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        setAttrPage(TITRE_PAGE, "Upload");
        setAttrPage(NOM_PAGE, "Nouvelle photo");
        try {
            if (UrlParams[1].equals("up")) {
                return upload(request);
            } else {
                return new ActionFlow("error", true);
            }
        } catch (IndexOutOfBoundsException ex) {
            return new ActionFlow("upload", attrPage, false);
        }
    }

    synchronized public ActionFlow upload(HttpServletRequest request) {
        ControlForm form = new ControlForm(request);
        String title = form.check("titre", ControlForm.NON_VIDE, "Donnez un titre à votre photo");
        String namealbum = form.check("album", ControlForm.NON_VIDE);
        String descr = form.check("description", ControlForm.NON_VIDE, "Une petite description?");
        form.close();
        if (form.getNbError() == 0) {
            Upload up = new Upload("file", new String[]{"jpg", "jpeg", "png"});
            AlbumMap mapalbum = new AlbumMap();
            AlbumBean album = (AlbumBean) mapalbum.getbyAttr("nameAlbum", namealbum);
            if (album != null) {
                String path = Tools.appPath + File.separator + FOLDER_ALBUM + namealbum;
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
                            setAttrPage(MESSAGE, "Votre photo est maintenant enregistrée.");
                        } else {
                            setAttrPage(MESSAGE, "L'upload ne s'est pas terminé correctement, une erreur serveur s'est produite");
                        }
                    } catch (IOException ex) {
                        setAttrPage(MESSAGE, "L'upload ne s'est pas terminé correctement, une erreur serveur s'est produite");
                        System.out.println("[ Erreur lecture image ] : " + ex.getMessage());
                    } catch (NullPointerException ex) {
                        f.delete();
                        setAttrPage(MESSAGE, "Votre fichier n'est pas une image !");
                        System.out.println("[ Erreur lecture image ] : " + ex.getMessage());
                    }
                } else {
                    String[] errorUpload = new String[]{
                        "Veuillez selectionner une photo à uploader.",
                        "Votre image doit faire 10Mo maximum.",
                        "L'upload ne s'est pas terminé correctement, une erreur serveur s'est produite.",
                        "L'upload ne s'est pas terminé correctement, une erreur serveur s'est produite."};
                    setAttrPage(MESSAGE, errorUpload[state - 1]);
                }
            } else {
                request.setAttribute(ErrorCommand.MESSAGE_ERROR, "L'album '" + namealbum + "' n'existe pas.");
                return new ActionFlow("error", attrPage, false);
            }
        }
        return new ActionFlow("upload", attrPage, false);
    }
}
