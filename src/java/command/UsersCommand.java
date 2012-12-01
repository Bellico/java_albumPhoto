package command;

import bdd.UserMap;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class UsersCommand extends Command {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        UserMap p = new UserMap();
        ArrayList<UserBean> tab = p.getAll();
        request.setAttribute("user", tab);

        setAttrPage(TITRE_PAGE, "Utilisateurs");
        setAttrPage(NOM_PAGE, "Liste des utilisateurs");
        return new ActionFlow("utilisateurs", attrPage, false);
    }
}