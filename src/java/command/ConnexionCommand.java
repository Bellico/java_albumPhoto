package command;

import bdd.UserMap;
import bean.UserBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import tools.Tools;

public class ConnexionCommand implements Command {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (!name.equals("") && !password.equals("")) {
            UserMap map = new UserMap();
            UserBean user = (UserBean) map.getbyAttr("name", name);
            if(user!=null){
                System.out.println(user.getPassword());
                System.out.println(Tools.crypt(password,Tools.MD5,true));
                if(user.getPassword().equals(Tools.crypt(password,Tools.MD5,true))){
                    HttpSession session =request.getSession();
                    session.setAttribute("user", user);
                }
            }
        }
        return new ActionFlow("index", true);
    }
}
