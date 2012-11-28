package command;

import javax.servlet.http.HttpServletRequest;

public class InscriptionCommand implements Command {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        String name = request.getParameter("name");
        String firstName = request.getParameter("firstName");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");

        return new ActionFlow("index", true);
    }
}