package command;

import javax.servlet.http.HttpServletRequest;

public class PartageCommand implements ICommand {

    

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        request.setAttribute(TITRE_PAGE, "Partager mes albums");
        request.setAttribute(NOM_PAGE, "Partager mes albums");
        return new ActionFlow("partage", false);
    }
}
