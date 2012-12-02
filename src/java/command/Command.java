package command;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

abstract public class Command {

    protected static final String TITRE_PAGE = "titlePage";
    protected static final String NOM_PAGE = "namePage";
    protected static final String MESSAGE = "messagePage";
    protected HashMap<String, String> attrPage = new HashMap<String, String>();

    Command() {
        attrPage.put(NOM_PAGE, "!!Non défini!!");
        attrPage.put(TITRE_PAGE, "!!Non défini!!");
    }

    protected void setAttrPage(String nameAttr, String value) {
        attrPage.put(nameAttr, value);
    }

    abstract public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams);
}