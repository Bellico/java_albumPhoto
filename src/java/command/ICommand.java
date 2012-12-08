package command;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public interface ICommand {

    public final static String TITRE_PAGE = "titlePage";
    public final static String NOM_PAGE = "namePage";
    public final static String USERS_SESSION = "user";
    public final static String PAGE_ERROR = "error";
    public final static String PAGE_INDEX = "index";

    public ActionFlow actionPerform(HttpServletRequest request, HashMap<Integer,String> urlParams);
}