package command;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand extends Command {

    public static final String MESSAGE_ERROR="messageError";

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        setAttrPage(TITRE_PAGE, "Erreur");
        setAttrPage(NOM_PAGE, "Oups !");
        return new ActionFlow("error", attrPage, false);
    }
}
