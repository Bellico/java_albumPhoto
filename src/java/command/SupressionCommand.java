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
        }
        return new ActionFlow("error", true);
    }

    public ActionFlow deletePhoto(HttpServletRequest request, int num) {
        PhotoBean photo = (PhotoBean) mapPhoto.getbyId(num);
        mapPhoto.delete(photo);
        return new ActionFlow(request.getHeader("referer"), true);
    }

    public ActionFlow deleteAlbum(HttpServletRequest request, int num) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(num);
        if (album != null) {
            ArrayList<RightBean> right = mapRight.getAllbyAttr("idAlbum", album.getIdAlbum());
            for (RightBean r : right) {
                mapRight.delete(r);
            }
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", album.getIdAlbum());
            for (PhotoBean ph : photos) {
                mapPhoto.delete(ph);
            }
            mapAlbum.delete(album);
        }
        return new ActionFlow(request.getHeader("referer"), true);
    }
}
