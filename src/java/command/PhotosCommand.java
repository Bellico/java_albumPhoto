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
    UserBean userSession = null;

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap<Integer, String> urlParams) {
        HttpSession session = request.getSession();
        userSession = (UserBean) session.getAttribute(USERS_SESSION);
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
        ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idStatut", 0);
        ArrayList<String[]> tab = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {
            UserBean user = (UserBean) mapUser.getbyAttr("idUser", al.getIdUser());
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", al.getIdAlbum());
            String albumCrypt = Tools.crypt(al.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
            for (PhotoBean ph : photos) {
                tab.add(new String[]{
                            UploadCommand.FOLDER_ALBUM + albumCrypt + "/" + ph.getImg(),
                            user.getName() + " " + user.getFirstName(),
                            al.getNameAlbum(),
                            ph.getTitle(),
                            ph.getDescr(),
                            ph.getDateCreated(),
                            ph.getDateLastUpdate(),
                            Integer.toString(ph.getIdPhoto()),
                            Integer.toString(al.getIdAlbum())
                        });
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
        String[] tab = new String[]{
            UploadCommand.FOLDER_ALBUM + albumCrypt + "/" + photo.getImg(),
            user.getName() + " " + user.getFirstName(),
            album.getNameAlbum(),
            photo.getTitle(),
            photo.getDescr(),
            Integer.toString(photo.getWidth()),
            Integer.toString(photo.getHeight()),
            photo.getDateCreated(),
            photo.getDateLastUpdate(),
            Integer.toString(album.getIdAlbum())};

        request.setAttribute("details", tab);
        request.setAttribute(TITRE_PAGE, "Details Photos");
        request.setAttribute(NOM_PAGE, "Details de la photo");
        return new ActionFlow("photos/details", false);
    }

    public ActionFlow mesPhotos(HttpServletRequest request) {
        ArrayList<AlbumBean> albumuser = mapAlbum.getAllbyAttr("idUser", userSession.getIdUser());
        ArrayList<String[]> tab = new ArrayList<String[]>();
        for (AlbumBean al : albumuser) {
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", al.getIdAlbum());
            String albumCrypt = Tools.crypt(al.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
            for (PhotoBean ph : photos) {
                tab.add(new String[]{
                            UploadCommand.FOLDER_ALBUM + albumCrypt + "/" + ph.getImg(),
                            userSession.getName() + " " + userSession.getFirstName(),
                            al.getNameAlbum(),
                            ph.getTitle(),
                            ph.getDescr(),
                            ph.getDateCreated(),
                            ph.getDateLastUpdate(),
                            Integer.toString(ph.getIdPhoto()),
                            Integer.toString(al.getIdAlbum()),
                            Integer.toString(userSession.getIdUser())
                        });
            }
        }

        request.setAttribute("listImg", tab);
        request.setAttribute(TITRE_PAGE, "Photos");
        request.setAttribute(NOM_PAGE, "Liste de mes Photos");
        return new ActionFlow("photos/photos", false);
    }
}