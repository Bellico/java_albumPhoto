package command;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements ICommand {

    public static final String MESSAGE_ERROR = "messageError";

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        request.setAttribute(TITRE_PAGE, "Erreur");
        request.setAttribute(NOM_PAGE, "Oups !");
        return new ActionFlow("error", false);
    }
}
