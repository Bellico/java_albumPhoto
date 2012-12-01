package command;

import javax.servlet.http.HttpServletRequest;

public class InscriptionCommand extends Command {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
         setAttrPage(TITRE_PAGE, "Inscription");
            setAttrPage(NOM_PAGE, "Inscrivez vous !");
         try {
            if (UrlParams[1].equals("new")) {
                return newUser(request);
            } else {
                return new ActionFlow("error", true);
            }
        } catch (IndexOutOfBoundsException ex) {
           
            return new ActionFlow("inscription", attrPage, false);
        }
    }
    
      public ActionFlow newUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String firstName = request.getParameter("firstName");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");

        return new ActionFlow("inscription", attrPage,false);
    }
    
}