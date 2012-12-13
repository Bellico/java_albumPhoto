package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.RightBean;
import bean.UserBean;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import tools.Tools;

public class SupressionCommand implements ICommand {

    private PhotoMap mapPhoto = new PhotoMap();
    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();
    private RightMap mapRight = new RightMap();
    UserBean userSession = null;

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap urlParams) {
        HttpSession session = request.getSession();
        userSession = (UserBean) session.getAttribute(USERS_SESSION);
        if (urlParams.get(1) != null && urlParams.get(2) != null) {
            if (urlParams.get(1).equals("photo")) {
                int numphoto = Tools.toInteger(urlParams.get(2));
                if (numphoto >= 0) {
                    return deletePhoto(request, numphoto);
                }
            }
            if (urlParams.get(1).equals("album")) {
                int numalbum = Tools.toInteger(urlParams.get(2));
                if (numalbum >= 0) {
                    return deleteAlbum(request, numalbum);
                }
            }
            if (urlParams.get(1).equals("utilisateur")) {
                int numuser = Tools.toInteger(urlParams.get(2));
                if (numuser >= 0) {
                    return deleteUser(request, numuser);
                }
            }
        }
        return new ActionFlow("error", true);
    }

    public ActionFlow deletePhoto(HttpServletRequest request, int num) {
        PhotoBean photo = (PhotoBean) mapPhoto.getbyId(num);
        if (photo != null) {
            AlbumBean album = (AlbumBean) mapAlbum.getbyId(photo.getIdAlbum());
            String nameCrypt = Tools.crypt(album.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
            String path = Tools.appPath + File.separator + UploadCommand.FOLDER_ALBUM + File.separator + nameCrypt;
            File f = new File(path + File.separator + photo.getImg());
            mapPhoto.delete(photo);
            f.delete();

            String redirect = request.getHeader("referer");
            return (redirect != null) ? new ActionFlow(request.getHeader("referer"), true) : new ActionFlow("photos/mesPhotos", true);
        }
        return new ActionFlow(PAGE_ERROR, true);
    }

    public ActionFlow deleteAlbum(HttpServletRequest request, int num) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(num);
        if (album != null) {
            String nameCrypt = Tools.crypt(album.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
            String path;
            File f;
            ArrayList<RightBean> right = mapRight.getAllbyAttr("idAlbum", album.getIdAlbum());
            for (RightBean r : right) {
                mapRight.delete(r);
            }
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", album.getIdAlbum());
            for (PhotoBean ph : photos) {
                path = Tools.appPath + File.separator + UploadCommand.FOLDER_ALBUM + File.separator + nameCrypt;
                f = new File(path + File.separator + ph.getImg());
                mapPhoto.delete(ph);
                f.delete();
            }
            path = Tools.appPath + File.separator + UploadCommand.FOLDER_ALBUM + File.separator + nameCrypt;
            f = new File(path);
            mapAlbum.delete(album);
            f.delete();

            String redirect = request.getHeader("referer");
            return (redirect != null) ? new ActionFlow(request.getHeader("referer"), true) : new ActionFlow("albums/mesAlbums", true);
        }
        return new ActionFlow(PAGE_ERROR, true);
    }

    public ActionFlow deleteUser(HttpServletRequest request, int num) {
        //Supprimer les droits
        UserBean user = (UserBean) mapUser.getbyId(num);
        mapUser.delete(user);
        return new ActionFlow("utilisateurs", true);
    }
}
