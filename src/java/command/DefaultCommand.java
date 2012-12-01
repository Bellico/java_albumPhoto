package command;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand extends Command {


    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        setAttrPage(TITRE_PAGE, "Accueil");
        setAttrPage(NOM_PAGE, "Accueil");
        return new ActionFlow("index", attrPage, false);
    }
}
