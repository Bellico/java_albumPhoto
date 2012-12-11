package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bdd.UserMap;
import bean.UserBean;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import tools.Tools;

public class ModificationCommand implements ICommand {

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
                    return modifPhoto(request, numphoto);
                }
            }
            if (urlParams.get(1).equals("album")) {
                int numalbum = Tools.toInteger(urlParams.get(2));
                if (numalbum >= 0) {
                    return modifAlbum(request, numalbum);
                }
            }
        }
        return new ActionFlow("error", true);
    }
    
     public ActionFlow modifPhoto(HttpServletRequest request, int num) {
         return new ActionFlow("photos/ajouter", false);
         
     }
     public ActionFlow modifAlbum(HttpServletRequest request, int num) {
          return new ActionFlow("albums/ajouter", false);
         
     }
}
