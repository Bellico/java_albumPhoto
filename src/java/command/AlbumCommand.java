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
    UserBean userSession = null;

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap<Integer, String> urlParams) {
        HttpSession session = request.getSession();
        userSession = (UserBean) session.getAttribute(USERS_SESSION);
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
        return new ActionFlow("albums/albums", false);
    }

    public ActionFlow mesAlbums(HttpServletRequest request) {
        ArrayList<AlbumBean> albums = mapAlbum.getAllbyAttr("idUser", userSession.getIdUser());
        ArrayList<String[]> tab = new ArrayList<String[]>();
        for (AlbumBean al : albums) {
            tab.add(new String[]{
                        al.getNameAlbum(),
                        userSession.getName() + " " + userSession.getFirstName(),
                        al.getDescr(),
                        Integer.toString(al.getNbPhoto()),
                        Integer.toString(al.getIdAlbum()),
                        Integer.toString(al.getIdUser())
                    });
        }

        request.setAttribute("listAlbum", tab);
        request.setAttribute(TITRE_PAGE, "Albums");
        request.setAttribute(NOM_PAGE, "Liste de mes Albums");
        return new ActionFlow("albums/albums", false);

    }

    public ActionFlow detailsAlbum(HttpServletRequest request, int numalbum) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(numalbum);
        UserBean user = (UserBean) mapUser.getbyId(album.getIdUser());
        String[] tab = new String[]{
            album.getNameAlbum(),
            user.getName() + " " + user.getFirstName(),
            album.getDescr(),
            Integer.toString(album.getNbPhoto()),
            (album.getIdStatut() == 0) ? "Public" : "Privé",
            Integer.toString(album.getIdUser())};

        ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idalbum", album.getIdAlbum());
        ArrayList<String[]> tab2 = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("IDALBUM", al.getIdAlbum());
            String albumCrypt = Tools.crypt(al.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
            for (PhotoBean ph : photos) {
                tab2.add(new String[]{
                            UploadCommand.FOLDER_ALBUM + albumCrypt + "/" + ph.getImg(),
                            user.getName() + " " + user.getFirstName(),
                            al.getNameAlbum(),
                            ph.getTitle(),
                            ph.getDescr(),
                            ph.getDateCreated(),
                            ph.getDateLastUpdate(),
                            Integer.toString(ph.getIdPhoto()),});
            }
        }

        request.setAttribute("details", tab);
        request.setAttribute("listImg", tab2);
        request.setAttribute(TITRE_PAGE, "Détails Album");
        request.setAttribute(NOM_PAGE, "Détails de l'album");
        return new ActionFlow("albums/details", false);
    }

    synchronized public ActionFlow ajoutAlbum(HttpServletRequest request) {
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

        request.setAttribute(TITRE_PAGE, "Albums");
        request.setAttribute(NOM_PAGE, "Creer un Album");
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