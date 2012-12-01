package command;

import bdd.UserMap;
import bean.UserBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConnexionCommand extends Command {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (name != null && !name.equals("") && password != null && !password.equals("")) {
            UserMap map = new UserMap();
            UserBean user = (UserBean) map.getbyAttr("name", name);
            if (user != null) {
                //if(user.getPassword().equals(Tools.crypt(password,Tools.MD5,true))){
                if (user.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                }
            }
        }
        return new ActionFlow("index", true);
    }
}
