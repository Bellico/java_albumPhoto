package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import tools.Tools;

public class PhotosCommand implements ICommand {

    private PhotoMap mapPhoto = new PhotoMap();
    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();
    UserBean userSession;
    UserBean admin;

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap<Integer, String> urlParams) {
        HttpSession session = request.getSession();
        userSession = (UserBean) session.getAttribute(USERS_SESSION);
        admin = (UserBean) session.getAttribute(ADMIN);

        if (urlParams.get(1) != null) {
            if (urlParams.get(1).equals("mesPhotos")) {
                return mesPhotos(request);
            }
            int numphoto = Tools.toInteger(urlParams.get(1));
            if (numphoto >= 0) {
                return detailsPhoto(request, numphoto);
            }
        }
        return listPhoto(request);
    }

    public ActionFlow listPhoto(HttpServletRequest request) {
        ArrayList<AlbumBean> albumpublic = (admin == null) ? mapAlbum.getAllbyAttr("idStatut", 0) : mapAlbum.getAll();
        ArrayList<HashMap<String, String>> tab = new ArrayList<HashMap<String, String>>();
        for (AlbumBean al : albumpublic) {
            UserBean user = (UserBean) mapUser.getbyAttr("idUser", al.getIdUser());
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", al.getIdAlbum());
            String albumCrypt = Tools.crypt(al.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
            for (PhotoBean ph : photos) {
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put("url", UploadCommand.FOLDER_ALBUM + "/" + albumCrypt + "/" + ph.getImg());
                temp.put("userName", user.getName() + " " + user.getFirstName());
                temp.put("nameAlbum", al.getNameAlbum());
                temp.put("titlePhoto", ph.getTitle());
                temp.put("photoDescr", ph.getDescr());
                temp.put("photoDateCreated", ph.getDateCreated());
                temp.put("photoLastUp", ph.getDateLastUpdate());
                temp.put("idPhoto", Integer.toString(ph.getIdPhoto()));
                temp.put("idAlbum", Integer.toString(al.getIdAlbum()));
                temp.put("albumStatut", (al.getIdStatut() == 0) ? "Public" : "Prive");
                tab.add(temp);
            }
        }

        request.setAttribute("listImg", tab);
        request.setAttribute(TITRE_PAGE, "Photos");
        request.setAttribute(NOM_PAGE, "Liste des photos");
        return new ActionFlow("photos/photos", false);
    }

    public ActionFlow detailsPhoto(HttpServletRequest request, int numphoto) {
        PhotoBean photo = (PhotoBean) mapPhoto.getbyId(numphoto);
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(photo.getIdAlbum());
        UserBean user = (UserBean) mapUser.getbyId(album.getIdUser());
        String albumCrypt = Tools.crypt(album.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
        HashMap<String, String> tab = new HashMap<String, String>();
        tab.put("url", UploadCommand.FOLDER_ALBUM + "/" + albumCrypt + "/" + photo.getImg());
        tab.put("userName", user.getName() + " " + user.getFirstName());
        tab.put("nameAlbum", album.getNameAlbum());
        tab.put("titlePhoto", photo.getTitle());
        tab.put("photoDescr", photo.getDescr());
        tab.put("widthPhoto", Integer.toString(photo.getWidth()));
        tab.put("heightPhoto", Integer.toString(photo.getHeight()));
        tab.put("photoDateCreated", photo.getDateCreated());
        tab.put("photoLastUp", photo.getDateLastUpdate());
        tab.put("idAlbum", Integer.toString(album.getIdAlbum()));

        request.setAttribute("details", tab);
        request.setAttribute(TITRE_PAGE, "Details Photos");
        request.setAttribute(NOM_PAGE, "Details de la photo");
        return new ActionFlow("photos/details", false);
    }

    public ActionFlow mesPhotos(HttpServletRequest request) {
        ArrayList<AlbumBean> albumuser = mapAlbum.getAllbyAttr("idUser", userSession.getIdUser());
        ArrayList<HashMap<String, String>> tab = new ArrayList<HashMap<String, String>>();
        for (AlbumBean al : albumuser) {
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", al.getIdAlbum());
            String albumCrypt = Tools.crypt(al.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
            for (PhotoBean ph : photos) {
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put("url", UploadCommand.FOLDER_ALBUM + "/" + albumCrypt + "/" + ph.getImg());
                temp.put("userName", userSession.getName() + " " + userSession.getFirstName());
                temp.put("nameAlbum", al.getNameAlbum());
                temp.put("titlePhoto", ph.getTitle());
                temp.put("photoDescr", ph.getDescr());
                temp.put("photoDateCreated", ph.getDateCreated());
                temp.put("photoLastUp", ph.getDateLastUpdate());
                temp.put("idPhoto", Integer.toString(ph.getIdPhoto()));
                temp.put("idAlbum", Integer.toString(al.getIdAlbum()));
                temp.put("idUser", Integer.toString(userSession.getIdUser()));
                temp.put("albumStatut", (al.getIdStatut() == 0) ? "Public" : "Prive");
                tab.add(temp);
            }
        }

        request.setAttribute("listImg", tab);
        request.setAttribute(TITRE_PAGE, "Photos");
        request.setAttribute(NOM_PAGE, "Liste de mes Photos");
        return new ActionFlow("photos/photos", false);
    }
}