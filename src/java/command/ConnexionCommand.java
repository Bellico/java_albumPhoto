package command;

import bdd.UserMap;
import bean.UserBean;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ControlForm;
import tools.Tools;

public class ConnexionCommand implements ICommand {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap UrlParams) {
        request.setAttribute(TITRE_PAGE, "Accueil");
        request.setAttribute(NOM_PAGE, "Accueil");

        ControlForm form = new ControlForm(request);
        String login = form.check("login", ControlForm.NON_VIDE);
        String password = form.check("password", ControlForm.NON_VIDE);
        if (form.getNbError() == 0) {
            UserMap map = new UserMap();
            UserBean user = (UserBean) map.getbyAttr("login", login);
            if (user != null) {
                if (user.getPassword().equals(Tools.crypt(password, Tools.MD5, true))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    return new ActionFlow("index", true);
                }
            }
        }
        form.setResult(ControlForm.FIELD_ERROR, "Identifiants Incorrects");
        form.close();
        return new ActionFlow("index", false);
    }
}
