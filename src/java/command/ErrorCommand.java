package command;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {

    private static HashMap<String, String> attrPage = new HashMap<String, String>();

    static {
        attrPage.put("tit1ePage", "Erreur");
        attrPage.put("namePage", "Oups !");
    }

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        return new ActionFlow("error.jsp", attrPage, false);
    }
}
