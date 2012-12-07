package command;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {

    public final String TITRE_PAGE = "titlePage";
    public final String NOM_PAGE = "namePage";

    public ActionFlow actionPerform(HttpServletRequest request, String[] urlParams);
}