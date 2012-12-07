package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminCommand implements ICommand {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        HttpSession session = request.getSession();
        session.setAttribute("admin", true);
        session.setAttribute("user", true);
        return new ActionFlow("index", true);
    }
}
