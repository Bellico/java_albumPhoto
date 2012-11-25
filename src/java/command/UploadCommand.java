package command;

import bean.PhotoBean;
import javax.servlet.http.HttpServletRequest;
import model.Upload;

public class UploadCommand extends Command {

    public static final String ATT_FICHIER = "fichier";
    public static final String ATT_FORM    = "form";
    
    @Override
    public boolean actionPerform(HttpServletRequest request) {
 System.out.print("terst");
        /* Préparation de l'objet formulaire */
        Upload form = new Upload();

        /* Traitement de la requête et récupération du bean en résultant */
        PhotoBean fichier = form.enregistrerFichier(request, "C:\\test");
        System.out.print(fichier.getTitle());

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_FICHIER, fichier);
        System.out.print("terst");
        return false;
    }
}
