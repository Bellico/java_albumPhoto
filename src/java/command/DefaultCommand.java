package command;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements ICommand {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap urlParams) {
        request.setAttribute(TITRE_PAGE, "Accueil");
        request.setAttribute(NOM_PAGE, "Accueil");
        return new ActionFlow("index", false);
    }
}
