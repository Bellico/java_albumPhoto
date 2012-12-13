package filter;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.RightBean;
import bean.UserBean;
import command.ActionFlow;
import command.ErrorCommand;
import command.ICommand;
import java.util.HashMap;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tools.Tools;

public class FilterFunctions {

    protected AlbumMap mapAlbum = new AlbumMap();
    protected PhotoMap mapPhoto = new PhotoMap();
    protected RightMap mapRight = new RightMap();
    protected UserMap mapUser = new UserMap();
    protected HttpSession session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HashMap<Integer, String> urlParams;

    protected void init_MyFilter(ServletRequest req, ServletResponse res) {
        request = (HttpServletRequest) req;
        response = (HttpServletResponse) res;
        session = request.getSession();
        urlParams = Tools.parseUrl(request.getRequestURI());
    }

    protected UserBean isConnect() {
        UserBean userSession = (UserBean) session.getAttribute(ICommand.USERS_SESSION);
        return userSession;
    }

    protected boolean isAdmin() {
        UserBean userSession = (UserBean) session.getAttribute(ICommand.ADMIN);
        return (userSession != null) ? true : false;
    }

    protected UserBean getUser(int id) {
        return (UserBean) mapUser.getbyId(id);
    }

    protected AlbumBean getAlbum(int id) {
        return (AlbumBean) mapAlbum.getbyId(id);
    }

    protected PhotoBean getPhoto(int id) {
        return (PhotoBean) mapPhoto.getbyId(id);
    }

    protected void sendError(String mError) {
        request.setAttribute(ICommand.TITRE_PAGE, "Erreur");
        request.setAttribute(ICommand.NOM_PAGE, "Oups !");
        request.setAttribute(ErrorCommand.MESSAGE_ERROR, mError);
        request.setAttribute("ACTIONFLOW", new ActionFlow(ICommand.PAGE_ERROR, false));
    }

    public RightBean Right_On_Album(UserBean user, AlbumBean album) {
        if (album == null) {
            return null;
        }
        if (isAdmin()) {
            return new RightBean(0, 0, true, true, true, true);
        }
        if (user != null) {
            if (album.getIdUser() == user.getIdUser()) {
                return new RightBean(user.getIdUser(), album.getIdUser(), true, true, true, true);
            }
            RightBean right = mapRight.get(user.getIdUser(), album.getIdAlbum());
            if (right != null) {
                return new RightBean(0, 0, true, false, false, false);
            }
        }
        if (album.getIdStatut() == 0) {
            return new RightBean(0, 0, true, false, false, false);
        } else {
            return new RightBean(0, 0, false, false, false, false);
        }
    }

    public RightBean Right_On_Album(UserBean user, PhotoBean photo) {
        if (photo == null) {
            return null;
        }
        if (isAdmin()) {
            return new RightBean(0, 0, true, true, true, true);
        }
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(photo.getIdAlbum());
        if (user != null) {
            if (album.getIdUser() == user.getIdUser()) {
                return new RightBean(user.getIdUser(), album.getIdUser(), true, true, true, true);
            }
            RightBean right = mapRight.get(user.getIdUser(), album.getIdAlbum());
            if (right != null) {
                return right;
            }
        }
        if (album.getIdStatut() == 0) {
            return new RightBean(0, 0, true, false, false, false);
        } else {
            return new RightBean(0, 0, false, false, false, false);
        }
    }

    public RightBean Right_On_User(UserBean user, UserBean user2) {
        if (user == null || user2 == null) {
            return null;
        }
        if (isAdmin()) {
            return new RightBean(0, 0, true, true, true, true);
        }
        if (user.getIdUser() == user2.getIdUser()) {
            return new RightBean(0, 0, true, false, true, false);
        } else {
            return new RightBean(0, 0, false, false, false, false);
        }
    }
}
