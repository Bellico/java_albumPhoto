package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.RightBean;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ControlForm;

public class PartageCommand implements ICommand {

    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();
    private PhotoMap mapPhoto = new PhotoMap();
    private RightMap mapRight = new RightMap();

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        request.setAttribute(TITRE_PAGE, "Partager mes albums");
        request.setAttribute(NOM_PAGE, "Partager mes albums");

        try {
            Integer numalbum = Integer.parseInt(UrlParams[1]);
            return partage(request, numalbum);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        } catch (NumberFormatException ex) {
            return new ActionFlow("error", true);
        }
    }

    public ActionFlow partage(HttpServletRequest request, int numalbum) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(numalbum);
        if (album == null) {
            request.setAttribute(ErrorCommand.MESSAGE_ERROR, "Cet album n'existe pas.");
            return new ActionFlow("error", false);
        }
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.setAttribute(ErrorCommand.MESSAGE_ERROR, "Veuillez vous connecter pour partager un album.");
            return new ActionFlow("error", false);
        } else {
            if (user.getIdUser() != album.getIdUser()) {

                request.setAttribute(ErrorCommand.MESSAGE_ERROR, "Vous n'avez aucun droit sur cet album.");
                return new ActionFlow("error", false);
            }
        }

        request.setAttribute("album", album);

        ArrayList<UserBean> listUsers = mapUser.getAll();
        request.setAttribute("listUsers", listUsers);

        if (request.getMethod().equals("POST")) {
            ControlForm form = new ControlForm(request);
            String name = request.getParameter("name");
            if (name.equals("")) {
                name = request.getParameter("nameSelect");
            }
            if (!name.equals("")) {
                user = (UserBean) mapUser.getbyAttr("login", name);
                if (user == null) {
                    form.setResult(ControlForm.RES_ERROR, "L'utilisateur '" + name + "' est inconnu.");
                } else {
                    boolean read = true;
                    boolean insert = (request.getParameter("insert") != null) ? true : false;
                    boolean update = (request.getParameter("update") != null) ? true : false;
                    boolean delete = (request.getParameter("delete") != null) ? true : false;
                    album.shareUser(user, read, insert, update, delete);
                    form.setResult(ControlForm.RES_VALID, "L'album '" + album.getNameAlbum() + "' est désormais partagé avec " + user.getName() + " " + user.getFirstName() + "!");
                }
            } else {
                form.setResult(ControlForm.RES_ERROR, "Selectionnez un utilisateur.");
            }
            form.close();
        }

        ArrayList<String[]> collaborateurs = new ArrayList<String[]>();
        ArrayList<RightBean> right = mapRight.getAllbyAttr("idAlbum", album.getIdAlbum());
        for (RightBean r : right) {
            UserBean u = (UserBean) mapUser.getbyId(r.getIdUser());
            collaborateurs.add(new String[]{
                        u.getName(),
                        u.getFirstName(),
                        u.getLogin(),
                        (r.isLire()) ? "validate" : "error",
                        (r.isInserer()) ? "validate" : "error",
                        (r.isModifier()) ? "validate" : "error",
                        (r.isSupprimer()) ? "validate" : "error"
                    });
        }
        request.setAttribute("collaborateurs", collaborateurs);
        return new ActionFlow("partage", false);
    }
}
