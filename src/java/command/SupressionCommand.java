package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
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
        if (urlParams.get(1) != null) {
            if (urlParams.get(1).equals("deletePhoto")) {
                int numphoto = Tools.toInteger(urlParams.get(2));
                if (numphoto >= 0) {
                    return PhotoDelete(request, numphoto);
                }
            }
            if (urlParams.get(1).equals("deleteAlbum")) {
                int numalbum = Tools.toInteger(urlParams.get(2));
                if (numalbum >= 0) {
                    return AlbumDelete(request, numalbum);
                }
            }
        }
        return new ActionFlow("index", true);
    }

    public ActionFlow PhotoDelete(HttpServletRequest request, int numphoto) {

        PhotoBean photo = (PhotoBean) mapPhoto.getbyId(numphoto);

        mapPhoto.delete(photo);


        return new ActionFlow("index", false);
    }

    public ActionFlow AlbumDelete(HttpServletRequest request, int albumnum) {


        AlbumBean album = (AlbumBean) mapAlbum.getbyId(albumnum);
        UserBean user = (UserBean) mapUser.getbyAttr("idUser", album.getIdUser());
        ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", album.getIdAlbum());
        for (PhotoBean ph : photos) {
            mapPhoto.delete(ph);
        }


        mapAlbum.delete(album);


        return new ActionFlow("index", false);
    }
}
