package command;

import bdd.UserMap;
import bean.UserBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ControlForm;
import tools.Tools;

public class ConnexionCommand implements ICommand {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        ControlForm form = new ControlForm(request);
        String login = form.check("login", ControlForm.NON_VIDE);
        String password = form.check("password", ControlForm.NON_VIDE);
        form.close();
        if (form.getNbError() == 0) {
            UserMap map = new UserMap();
            UserBean user = (UserBean) map.getbyAttr("login", login);
            if (user != null) {
                if (user.getPassword().equals(Tools.crypt(password, Tools.MD5, true))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                }
            }
        }
        return new ActionFlow("index", true);
    }
}
