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

public class AdminCommand implements ICommand {

    UserMap mapUser = new UserMap();
    AlbumMap mapAlbum = new AlbumMap();
    PhotoMap mapPhoto = new PhotoMap();
    RightMap mapRight = new RightMap();

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap urlParams) {
        UserBean user = creationAdmin();
        HttpSession session = request.getSession();
        session.setAttribute(ADMIN, user);
        session.setAttribute(USERS_SESSION, user);
        if (urlParams.get(1) != null) {
            if (urlParams.get(1).equals("deleteUsers")) {
                deletePhotos();
                deleteRight();
                deleteAlbums();
                deleteUsers();
                return new ActionFlow("utilisateurs", true);
            }
            if (urlParams.get(1).equals("deleteAlbums")) {
                deletePhotos();
                deleteRight();
                deleteAlbums();
                return new ActionFlow("albums", true);
            }
            if (urlParams.get(1).equals("deletePhotos")) {
                deletePhotos();
                return new ActionFlow("photos", true);
            }
            if (urlParams.get(1).equals("deleteRight")) {
                deleteRight();
            }
        }
        return new ActionFlow("index", true);
    }

    public void deleteUsers() {
        ArrayList<UserBean> list = mapUser.getAll();
        for (UserBean u : list) {
            mapUser.delete(u);
        }
    }

    public void deleteAlbums() {
        ArrayList<AlbumBean> list = mapAlbum.getAll();
        for (AlbumBean a : list) {
            mapAlbum.delete(a);
        }
    }

    public void deletePhotos() {
        ArrayList<PhotoBean> list = mapPhoto.getAll();
        for (PhotoBean p : list) {
            mapPhoto.delete(p);
        }
    }

    public void deleteRight() {
        ArrayList<RightBean> list = mapRight.getAll();
        for (RightBean r : list) {
            mapRight.delete(r);
        }
    }

    public UserBean creationAdmin() {
        UserBean user = (UserBean) mapUser.getbyAttr("login", "ADMIN");
        if (user == null) {
            user = new UserBean();
            user.setName("ADMIN");
            user.setFirstName("ADMIN");
            user.setLogin("ADMIN");
            user.setPassword(Tools.crypt("ADMIN", Tools.MD5, true));
            mapUser.save(user);
            user = (UserBean) mapUser.getbyAttr("login", "ADMIN");
        }
        return user;
    }
}
