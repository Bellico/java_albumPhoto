package command;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    private static HashMap<String, String> attrPage = new HashMap<String, String>();

    static {
        attrPage.put("tit1ePage", "Accueil");
        attrPage.put("namePage", "Accueil");
    }

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        return new ActionFlow("index.jsp", attrPage, false);
    }
}
