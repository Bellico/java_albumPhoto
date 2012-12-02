package command;

import bdd.UserMap;
import bean.UserBean;
import javax.servlet.http.HttpServletRequest;
import model.ControlForm;
import tools.Tools;

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
        ControlForm form = new ControlForm(request);
        String nom = form.check("nom", ControlForm.NON_VIDE, "Précisé un nom");
        String prenom = form.check("prenom", ControlForm.NON_VIDE, "Précisé un prenom");
        String login = form.check("login", ControlForm.NON_VIDE, "Précisé un login");
        String pass = form.check("pass", ControlForm.NON_VIDE, "Précisé un mdp");
        form.compare("pass", "passc", "mdp non valide");
        form.close();
        if (form.getNbError() == 0) {
            UserBean user = new UserBean();
            user.setName(nom);
            user.setFirstName(prenom);
            user.setLogin(login);
            user.setPassword(Tools.crypt(pass,Tools.MD5,true));
            UserMap map = new UserMap();
            if (map.save(user) == 1) {
                setAttrPage(MESSAGE, "Inscription réussie!");
            } else {
                setAttrPage(MESSAGE, "L'incription ne s'est pas terminée correctement, une erreur serveur s'est produite");
            }
        }
        return new ActionFlow("inscription", attrPage, false);
    }
}