package command;

import bdd.UserMap;
import bean.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsersCommand implements ICommand {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap urlParams) {
        request.setAttribute(TITRE_PAGE, "Utilisateurs");
        request.setAttribute(NOM_PAGE, "Liste des utilisateurs");
        if (urlParams.get(1) != null) {
            if (urlParams.get(1).equals("logOut")) {
                HttpSession session = request.getSession();
                session.invalidate();
                return new ActionFlow("index", true);
            }
        }
        UserMap p = new UserMap();
        ArrayList<UserBean> tab = p.getAll(null);
        request.setAttribute("user", tab);
        return new ActionFlow("users/utilisateurs", false);
    }
}