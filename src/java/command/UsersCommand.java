package command;

import bdd.UserMap;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class UsersCommand implements Command {
    

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        
        request.setAttribute("nompage", "Liste des Util");

        UserMap p = new UserMap();
        ArrayList<UserBean> tab = p.getAll();
        request.setAttribute("User", tab);
        request.setAttribute("listAlbum", tab);

        return new ActionFlow("utilisateurs.jsp", false);
    }
}