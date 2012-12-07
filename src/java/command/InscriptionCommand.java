package command;

import bdd.UserMap;
import bean.UserBean;
import javax.servlet.http.HttpServletRequest;
import model.ControlForm;
import tools.Tools;

public class InscriptionCommand implements ICommand {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        request.setAttribute(TITRE_PAGE, "Inscription");
        request.setAttribute(NOM_PAGE, "Inscrivez vous !");
        try {
            if (UrlParams[1].equals("new")) {
                return newUser(request);
            } else {
                return new ActionFlow("error", true);
            }
        } catch (IndexOutOfBoundsException ex) {
            return new ActionFlow("inscription", false);
        }
    }

    synchronized public ActionFlow newUser(HttpServletRequest request) {
        ControlForm form = new ControlForm(request);
        String nom = form.check("nom", ControlForm.NON_VIDE, "Précisez un nom");
        String prenom = form.check("prenom", ControlForm.NON_VIDE, "Précisez un prenom");
        String login = form.check("login", ControlForm.NON_VIDE, "Précisez un login");
        String pass = form.check("pass", ControlForm.NON_VIDE, "Précisez un mdp");
        form.compare("passc", "pass", "Les mots de passe ne sont pas les mêmes");
        if (form.getNbError() == 0) {
            UserMap map = new UserMap();
            UserBean user = (UserBean) map.getbyAttr("login", login);
            if (user == null) {
                user = new UserBean();
                user.setName(nom);
                user.setFirstName(prenom);
                user.setLogin(login);
                user.setPassword(Tools.crypt(pass, Tools.MD5, true));
                if (map.save(user) == 1) {
                    form.clean();
                    form.setResult(ControlForm.RES_VALID, "Inscription réussie!");
                } else {
                    form.setResult(ControlForm.RES_ERROR, "L'incription ne s'est pas terminée correctement, une erreur serveur s'est produite");
                }
            } else {
                form.setResult(ControlForm.RES_ERROR, "Ce login est déja utilisé.");
            }
        }
        form.close();
        return new ActionFlow("inscription", false);
    }
}