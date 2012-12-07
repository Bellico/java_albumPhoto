package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ControlForm;

public class AlbumCommand implements ICommand {

    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();
    private PhotoMap mapPhoto = new PhotoMap();

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        try {
            if (UrlParams[1].equals("nouveau")) {
                request.setAttribute(TITRE_PAGE, "Albums");
                request.setAttribute(NOM_PAGE, "Creer un Album");
                if (request.getMethod().equals("GET")) {
                    return new ActionFlow("ajoutAlbum", false);
                }
                return newAlbum(request);
            } else {
                Integer numalbum = Integer.parseInt(UrlParams[1]);
                return detailsAlbum(request, numalbum);
            }
        } catch (IndexOutOfBoundsException ex) {
            return listAlbum(request);
        } catch (NumberFormatException ex) {
            return new ActionFlow("error", true);
        }
    }

    public ActionFlow listAlbum(HttpServletRequest request) {
        ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idStatut", 0);
        ArrayList<String[]> tab = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {
            UserBean user = (UserBean) mapUser.getbyAttr("idUser", al.getIdUser());
            tab.add(new String[]{
                        al.getNameAlbum(),
                        user.getName() + " " + user.getFirstName(),
                        al.getDescr(),
                        Integer.toString(al.getNbPhoto()),
                        Integer.toString(al.getIdAlbum())
                    });
        }
        request.setAttribute("listAlbum", tab);
        request.setAttribute(TITRE_PAGE, "Albums");
        request.setAttribute(NOM_PAGE, "Liste des Albums");
        return new ActionFlow("albums", false);
    }

    public ActionFlow detailsAlbum(HttpServletRequest request, int numalbum) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(numalbum);
        if (album == null) {
            request.setAttribute(ErrorCommand.MESSAGE_ERROR, "Cet album n'existe pas.");
            return new ActionFlow("error", false);
        }
        UserBean user = (UserBean) mapUser.getbyId(album.getIdUser());
        String[] tab = new String[]{
            user.getName() + " " + user.getFirstName(),
            album.getNameAlbum(),
            album.getDescr(),
            Integer.toString(album.getNbPhoto())};
        request.setAttribute("details", tab);

        ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idalbum", album.getIdAlbum());
        ArrayList<String[]> tab2 = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {

            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("IDALBUM", al.getIdAlbum());
            for (PhotoBean ph : photos) {
                tab2.add(new String[]{
                            UploadCommand.FOLDER_ALBUM + al.getNameAlbum() + "/" + ph.getImg(),
                            user.getName() + " " + user.getFirstName(),
                            al.getNameAlbum(),
                            ph.getTitle(),
                            ph.getDescr(),
                            ph.getDateCreated(),
                            ph.getDateLastUpdate(),
                            Integer.toString(ph.getIdPhoto())
                        });
            }
        }
        request.setAttribute("listImg", tab2);
        request.setAttribute(TITRE_PAGE, "Détails Album");
        request.setAttribute(NOM_PAGE, "Détails de l'album");
        return new ActionFlow("detailsAlbum", false);
    }

    public ActionFlow newAlbum(HttpServletRequest request) {
        ControlForm form = new ControlForm(request);
        String name = form.check("name", ControlForm.NON_VIDE, "Donnez un titre à votre album.");
        String description = form.check("description", ControlForm.NON_VIDE, "Une petite description ?");

        if (form.getNbError() == 0) {
            HttpSession session = request.getSession();
            UserBean user = (UserBean) session.getAttribute("user");
            AlbumBean album = new AlbumBean();
            album.setNameAlbum(name);
            album.setDescr(description);
            album.setIdStatut(0);
            album.setIdUser(user.getIdUser());
            if (mapAlbum.save(album) == 1) {
                form.clean();
                form.setResult(ControlForm.RES_VALID, "Album ajouté !");
            } else {
                form.setResult(ControlForm.RES_ERROR, "L'operation ne s'est pas terminée correctement, une erreur serveur s'est produite.");
            }
        }
        form.close();
        return new ActionFlow("ajoutAlbum", false);
    }
}