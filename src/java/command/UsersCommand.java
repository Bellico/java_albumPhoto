package command;

import bdd.UserMap;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsersCommand implements ICommand {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        try {
            if (UrlParams[1].equals("logOut")) {
                HttpSession session = request.getSession();
                session.invalidate();
                return new ActionFlow("index", true);
            }
        } catch (IndexOutOfBoundsException ex) {
            UserMap p = new UserMap();
            ArrayList<UserBean> tab = p.getAll();
            request.setAttribute("user", tab);

            request.setAttribute(TITRE_PAGE, "Utilisateurs");
            request.setAttribute(NOM_PAGE, "Liste des utilisateurs");          
        }
         return new ActionFlow("utilisateurs", false);
    }
}