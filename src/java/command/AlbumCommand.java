package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.RightBean;
import bean.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ControlForm;
import tools.Tools;

public class AlbumCommand implements ICommand {

    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();
    private PhotoMap mapPhoto = new PhotoMap();
    private RightMap mapRight = new RightMap();
    UserBean userSession;
    UserBean admin;

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap<Integer, String> urlParams) {
        HttpSession session = request.getSession();
        userSession = (UserBean) session.getAttribute(USERS_SESSION);
        admin = (UserBean) session.getAttribute(ADMIN);

        if (urlParams.get(1) != null) {
            if (urlParams.get(1).equals("nouveau")) {
                return ajoutAlbum(request);
            }
            if (urlParams.get(1).equals("mesAlbums")) {
                return mesAlbums(request);
            }
            int numalbum = Tools.toInteger(urlParams.get(1));
            if (numalbum >= 0) {
                return detailsAlbum(request, numalbum);
            }
        }
        return listAlbum(request);
    }

    public ActionFlow listAlbum(HttpServletRequest request) {
        ArrayList<AlbumBean> albumpublic = (admin == null) ? mapAlbum.getAllbyAttr("idStatut", 0) : mapAlbum.getAll();
        ArrayList<HashMap<String, String>> tab = new ArrayList<HashMap<String, String>>();
        for (AlbumBean al : albumpublic) {
            UserBean user = (UserBean) mapUser.getbyAttr("idUser", al.getIdUser());
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("idAlbum", Integer.toString(al.getIdAlbum()));
            temp.put("nameAlbum", al.getNameAlbum());
            temp.put("userName", user.getName() + " " + user.getFirstName());
            temp.put("albumDescr", al.getDescr());
            temp.put("nbPhoto", Integer.toString(al.getNbPhoto()));
            temp.put("albumStatut", (al.getIdStatut() == 0) ? "Public" : "Prive");
            tab.add(temp);
        }

        request.setAttribute("listAlbum", tab);
        request.setAttribute(TITRE_PAGE, "Albums");
        request.setAttribute(NOM_PAGE, "Liste des Albums");
        return new ActionFlow("albums/albums", false);
    }

    public ActionFlow mesAlbums(HttpServletRequest request) {
        ArrayList<AlbumBean> albums = mapAlbum.getAllbyAttr("idUser", userSession.getIdUser());
        ArrayList<HashMap<String, String>> tab = new ArrayList<HashMap<String, String>>();
        for (AlbumBean al : albums) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("idAlbum", Integer.toString(al.getIdAlbum()));
            temp.put("idUser", Integer.toString(al.getIdUser()));
            temp.put("nameAlbum", al.getNameAlbum());
            temp.put("userName", userSession.getName() + " " + userSession.getFirstName());
            temp.put("albumDescr", al.getDescr());
            temp.put("nbPhoto", Integer.toString(al.getNbPhoto()));
            temp.put("albumStatut", (al.getIdStatut() == 0) ? "Public" : "Prive");
            tab.add(temp);
        }

        request.setAttribute("listAlbum", tab);
        request.setAttribute(TITRE_PAGE, "Albums");
        request.setAttribute(NOM_PAGE, "Liste de mes Albums");
        return new ActionFlow("albums/albums", false);

    }

    public ActionFlow detailsAlbum(HttpServletRequest request, int numalbum) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(numalbum);
        UserBean user = (UserBean) mapUser.getbyId(album.getIdUser());

        HashMap<String, String> tab = new HashMap<String, String>();
        tab.put("idUser", Integer.toString(album.getIdUser()));
        tab.put("nameAlbum", album.getNameAlbum());
        tab.put("userName", user.getName() + " " + user.getFirstName());
        tab.put("albumDescr", album.getDescr());
        tab.put("nbPhoto", Integer.toString(album.getNbPhoto()));
        tab.put("albumStatut", (album.getIdStatut() == 0) ? "Public" : "Prive");

        ArrayList<HashMap<String, String>> tab2 = new ArrayList<HashMap<String, String>>();
        ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idalbum", album.getIdAlbum());
        String albumCrypt = Tools.crypt(album.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
        RightBean right = (userSession != null) ? mapRight.get(userSession.getIdUser(), album.getIdAlbum()) : null;
        for (PhotoBean ph : photos) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("url", UploadCommand.FOLDER_ALBUM + "/" + albumCrypt + "/" + ph.getImg());
            temp.put("userName", user.getName() + " " + user.getFirstName());
            temp.put("nameAlbum", album.getNameAlbum());
            temp.put("titlePhoto", ph.getTitle());
            temp.put("PhotoDescr", ph.getDescr());
            temp.put("PhotoDateCreated", ph.getDateCreated());
            temp.put("PhotoLastUp", ph.getDateLastUpdate());
            temp.put("idPhoto", Integer.toString(ph.getIdPhoto()));
            temp.put("isUpdate", (right != null && right.isModifier()) ? "1" : "0");
            temp.put("isDelete", (right != null && right.isSupprimer()) ? "1" : "0");
            tab2.add(temp);
        }

        request.setAttribute("details", tab);
        request.setAttribute("listImg", tab2);
        request.setAttribute(TITRE_PAGE, "Détails Album");
        request.setAttribute(NOM_PAGE, "Détails de l'album");
        return new ActionFlow("albums/details", false);
    }

    synchronized public ActionFlow ajoutAlbum(HttpServletRequest request) {
        request.setAttribute(TITRE_PAGE, "Albums");
        request.setAttribute(NOM_PAGE, "Creer un Album");
        if (request.getMethod().equals("GET")) {
            return new ActionFlow("albums/ajouter", false);
        }

        ControlForm form = new ControlForm(request);
        String name = form.check("name", ControlForm.NON_VIDE, "Donnez un titre à votre album.");
        String description = form.check("description", ControlForm.NON_VIDE, "Une petite description ?");
        String statut = form.check("statut", new String[]{"0", "1"});
        statut = form.check("statut", ControlForm.ENTIER);

        if (form.getNbError() == 0) {
            if (!findAlbum_User(name)) {
                AlbumBean album = new AlbumBean();
                album.setNameAlbum(name);
                album.setDescr(description);
                album.setIdStatut(Integer.parseInt(statut));
                album.setIdUser(userSession.getIdUser());
                if (mapAlbum.save(album) == 1) {
                    form.clean();
                    form.setResult(ControlForm.RES_VALID, "Album ajouté !");
                } else {
                    form.setResult(ControlForm.RES_ERROR, "L'operation ne s'est pas terminée correctement, une erreur serveur s'est produite.");
                }
            } else {
                form.setResult(ControlForm.RES_ERROR, "Vous avez déja un album  : " + name);
            }
        }
        form.close();

        return new ActionFlow("albums/ajouter", false);
    }

    public boolean findAlbum_User(String name) {
        ArrayList<AlbumBean> listalbum = mapAlbum.getAllbyAttr("idUser", userSession.getIdUser());
        Iterator it = listalbum.iterator();
        boolean find = false;
        while (it.hasNext() && !find) {
            AlbumBean a = (AlbumBean) it.next();
            if ((a.getNameAlbum().toLowerCase()).equals(name)) {
                find = true;
            }
        }
        return find;
    }
}