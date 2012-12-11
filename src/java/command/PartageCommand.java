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
    UserBean userSession = null;

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap urlParams) {
        HttpSession session = request.getSession();
        userSession = (UserBean) session.getAttribute("user");

        int numalbum = Tools.toInteger(urlParams.get(1));
        if (numalbum >= 0) {
            return partage(request, numalbum);
        }

        return listePartage(request);
    }

    synchronized public ActionFlow partage(HttpServletRequest request, int numalbum) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(numalbum);

        int iduser = Tools.toInteger(request.getParameter("supp"));
        if (iduser >= 0) {
            album.removeUser((UserBean) mapUser.getbyId(iduser));
        }

        if (request.getMethod().equals("POST")) {
            ControlForm form = new ControlForm(request);
            String name = request.getParameter("name");
            if (name.equals("")) {
                name = request.getParameter("nameSelect");
            }
            if (!name.equals("")) {
                UserBean user = (UserBean) mapUser.getbyAttr("login", name.toLowerCase());
                if (user == null) {
                    form.setResult(ControlForm.RES_ERROR, "L'utilisateur '" + name + "' est inconnu.");
                } else if (user.getIdUser() == userSession.getIdUser()) {
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

        ArrayList<HashMap<String, String>> collaborateurs = new ArrayList<HashMap<String, String>>();
        ArrayList<RightBean> right = mapRight.getAllbyAttr("idAlbum", album.getIdAlbum());
        for (RightBean r : right) {
            HashMap<String, String> temp = new HashMap<String, String>();
            UserBean u = (UserBean) mapUser.getbyId(r.getIdUser());
            temp.put("userName", u.getName());
            temp.put("userFistName", u.getFirstName());
            temp.put("userLogin", u.getLogin());
            temp.put("isRead", (r.isLire()) ? "1" : "0");
            temp.put("isInsert", (r.isInserer()) ? "1" : "0");
            temp.put("isUpdate", (r.isModifier()) ? "1" : "0");
            temp.put("isDelete", (r.isSupprimer()) ? "1" : "0");
            temp.put("idUser", Integer.toString(r.getIdUser()));
            collaborateurs.add(temp);
        }

        request.setAttribute(TITRE_PAGE, "Partager mes albums");
        request.setAttribute(NOM_PAGE, "Partager mes albums");
        request.setAttribute("album", album);
        ArrayList<UserBean> listUsers = mapUser.getAll(null);
        request.setAttribute("listUsers", listUsers);
        request.setAttribute("collaborateurs", collaborateurs);
        return new ActionFlow("partage", false);
    }

    public ActionFlow listePartage(HttpServletRequest request) {
        ArrayList<RightBean> listright = mapRight.getAllbyAttr("idUser", userSession.getIdUser());
        ArrayList<HashMap<String, String>> tab = new ArrayList<HashMap<String, String>>();
        for (RightBean r : listright) {
            AlbumBean al = (AlbumBean) mapAlbum.getbyAttr("idAlbum", r.getIdAlbum());
            UserBean user = (UserBean) mapUser.getbyId(al.getIdUser());
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("idAlbum", Integer.toString(al.getIdAlbum()));
            temp.put("idUser", Integer.toString(al.getIdUser()));
            temp.put("nameAlbum", al.getNameAlbum());
            temp.put("userName", user.getName() + " " + user.getFirstName());
            temp.put("albumDescr", al.getDescr());
            temp.put("nbPhoto", Integer.toString(al.getNbPhoto()));
            temp.put("albumStatut", (al.getIdStatut() == 0) ? "Public" : "Prive");
            tab.add(temp);
        }

        request.setAttribute("listAlbum", tab);
        request.setAttribute(TITRE_PAGE, "Albums partagés");
        request.setAttribute(NOM_PAGE, "Albums partagés");
        return new ActionFlow("albums/albums", false);

    }
}
