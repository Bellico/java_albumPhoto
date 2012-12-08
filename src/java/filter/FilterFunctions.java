package filter;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import command.ActionFlow;
import command.ErrorCommand;
import command.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FilterFunctions {

    protected UserBean isConnect(HttpSession session) {
        UserBean userSession = (UserBean) session.getAttribute(ICommand.USERS_SESSION);
        return userSession;
    }

    protected UserBean getUser(int id) {
        UserMap map = new UserMap();
        return (UserBean) map.getbyId(id);
    }

    protected AlbumBean getAlbum(int id) {
        AlbumMap map = new AlbumMap();
        return (AlbumBean) map.getbyId(id);
    }

    protected PhotoBean getPhoto(int id) {
        PhotoMap map = new PhotoMap();
        return (PhotoBean) map.getbyId(id);
    }

    protected void sendError(HttpServletRequest request, String mError) {
        request.setAttribute(ICommand.TITRE_PAGE, "Erreur");
        request.setAttribute(ICommand.NOM_PAGE, "Oups !");
        request.setAttribute(ErrorCommand.MESSAGE_ERROR, mError);
        request.setAttribute("ACTIONFLOW", new ActionFlow(ICommand.PAGE_ERROR, false));
    }
}
