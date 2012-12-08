package command;

import bdd.AlbumMap;
import bdd.RightMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.RightBean;
import bean.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ControlForm;
import tools.Tools;

public class PartageCommand implements ICommand {

    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();
    private RightMap mapRight = new RightMap();
    UserBean UserSession = null;

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap urlParams) {
        HttpSession session = request.getSession();
        UserSession = (UserBean) session.getAttribute("user");
        int numalbum = Tools.toInteger(urlParams.get(1));
        if (numalbum >= 0) {
            return partage(request, numalbum);
        }
        return null;
    }

    synchronized public ActionFlow partage(HttpServletRequest request, int numalbum) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(numalbum);

        if (request.getMethod().equals("POST")) {
            ControlForm form = new ControlForm(request);
            String name = request.getParameter("name");
            if (name.equals("")) {
                name = request.getParameter("nameSelect");
            }
            if (!name.equals("")) {
               UserBean user = (UserBean) mapUser.getbyAttr("login", name);
                if (user == null) {
                    form.setResult(ControlForm.RES_ERROR, "L'utilisateur '" + name + "' est inconnu.");
                } else if (user.getIdUser() == UserSession.getIdUser()) {
                    form.setResult(ControlForm.RES_ERROR, "Vous ne pouvez pas partager un album avec vous même.");
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
                        (r.isLire()) ? "1" : "0",
                        (r.isInserer()) ? "1" : "0",
                        (r.isModifier()) ? "1" : "0",
                        (r.isSupprimer()) ? "1" : "0"
                    });
        }

        request.setAttribute(TITRE_PAGE, "Partager mes albums");
        request.setAttribute(NOM_PAGE, "Partager mes albums");
        request.setAttribute("album", album);
        ArrayList<UserBean> listUsers = mapUser.getAll();
        request.setAttribute("listUsers", listUsers);
        request.setAttribute("collaborateurs", collaborateurs);
        return new ActionFlow("partage", false);
    }
}
