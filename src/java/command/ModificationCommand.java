package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ControlForm;
import model.ResultForm;
import tools.Tools;

public class ModificationCommand implements ICommand {

    private PhotoMap mapPhoto = new PhotoMap();
    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();
    UserBean userSession = null;

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap urlParams) {
        HttpSession session = request.getSession();
        userSession = (UserBean) session.getAttribute(USERS_SESSION);
        if (urlParams.get(1) != null && urlParams.get(2) != null) {
            if (urlParams.get(1).equals("photo")) {
                int numphoto = Tools.toInteger(urlParams.get(2));
                if (numphoto >= 0) {
                    return modifPhoto(request, numphoto);
                }
            }
            if (urlParams.get(1).equals("album")) {
                int numalbum = Tools.toInteger(urlParams.get(2));
                if (numalbum >= 0) {
                    return modifAlbum(request, numalbum);
                }
            }
            if (urlParams.get(1).equals("utilisateur")) {
                int numUser = Tools.toInteger(urlParams.get(2));
                if (numUser >= 0) {
                    return modifUser(request, numUser);
                }
            }
        }
        return new ActionFlow("error", true);
    }

    public ActionFlow modifPhoto(HttpServletRequest request, int num) {
        ControlForm form = new ControlForm(request);
        PhotoBean photo = (PhotoBean) mapPhoto.getbyId(num);
        if (request.getMethod().equals("POST")) {
            String title = form.check("titre", ControlForm.NON_VIDE, "Votre titre est vide.");
            String descr = form.check("description", ControlForm.NON_VIDE, "Et la description ?");
            if (form.getNbError() == 0) {
                photo.setTitle(title);
                photo.setDescr(descr);
                photo.setKeyword(Tools.generate_KeyWord(descr));
                photo.setDate_lastUpdate();
                photo.setTime_lastUpdate();
                System.out.print("okk");
                mapPhoto.save(photo);
                return new ActionFlow("photos/" + photo.getIdPhoto(), true);
            }
        } else {
            ResultForm res = form.getResultForm();
            res.setField("titre", photo.getTitle());
            res.setField("description", photo.getDescr());
        }
        form.close();

        AlbumBean album = (AlbumBean) mapAlbum.getbyId(photo.getIdAlbum());
        String albumCrypt = Tools.crypt(album.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
        photo.setImg(UploadCommand.FOLDER_ALBUM + "/" + albumCrypt + "/" + photo.getImg());

        request.setAttribute("photo", photo);
        request.setAttribute(TITRE_PAGE, "Modification Image");
        request.setAttribute(NOM_PAGE, "Modifier votre image");
        return new ActionFlow("photos/modifier", false);

    }

    public ActionFlow modifAlbum(HttpServletRequest request, int num) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(num);
        ControlForm form = new ControlForm(request);

        if (request.getMethod().equals("POST")) {
            String title = form.check("name", ControlForm.NON_VIDE, "Votre titre est vide.");
            String descr = form.check("description", ControlForm.NON_VIDE, "Et la description ?");
            String statut = form.check("statut", new String[]{"0", "1"});
            if (form.getNbError() == 0) {
                album.setNameAlbum(title);
                album.setDescr(descr);
                album.setIdStatut(Integer.parseInt(statut));
                mapAlbum.save(album);
                return new ActionFlow("albums/" + album.getIdAlbum(), true);
            }
        } else {
            ResultForm res = form.getResultForm();
            res.setField("name", album.getNameAlbum());
            res.setField("description", album.getDescr());
            res.setField("statut", Integer.toString(album.getIdStatut()));
        }
        form.close();

        request.setAttribute("album", album);
        request.setAttribute(TITRE_PAGE, "Modification Album");
        request.setAttribute(NOM_PAGE, "Modifier votre Album");
        return new ActionFlow("albums/modifier", false);
    }

    public ActionFlow modifUser(HttpServletRequest request, int num) {
        UserBean user = (UserBean) mapUser.getbyId(num);
        ControlForm form = new ControlForm(request);

        if (request.getMethod().equals("POST")) {
            String nom = form.check("nom", ControlForm.NON_VIDE, "Précisez un nom.");
            String prenom = form.check("prenom", ControlForm.NON_VIDE, "Précisez un prenom.");
            String login = form.check("login", ControlForm.NON_VIDE, "Précisez un login.");
            String pass = null;
            if (!request.getParameter("pass").isEmpty()) {
                pass = form.compare("passc", "pass", "Vous devez confirmer votre nouveau mot de passe.");
            }

            if (form.getNbError() == 0) {
                UserBean u = (UserBean) mapUser.getbyAttr("login", login);
                if (u == null || u.getIdUser() == user.getIdUser()) {
                    user.setName(nom);
                    user.setFirstName(prenom);
                    user.setLogin(login.toLowerCase());
                    user.setDate_lastUpdate();
                    user.setTime_lastUpdate();
                    if (pass != null) {
                        user.setPassword(Tools.crypt(pass, Tools.MD5, true));
                    }
                    // user.setPassword(Tools.crypt(pass, Tools.MD5, true));
                    mapUser.save(user);
                    return new ActionFlow("utilisateurs", true);
                } else {
                    form.setResult(ControlForm.RES_ERROR, "Ce login est déja utilisé.");
                }
            }
        } else {
            ResultForm res = form.getResultForm();
            res.setField("nom", user.getName());
            res.setField("prenom", user.getFirstName());
            res.setField("login", user.getLogin());
        }
        form.close();

        request.setAttribute("user", user);
        request.setAttribute(TITRE_PAGE, "Modification Utilisateur");
        request.setAttribute(NOM_PAGE, "Modifier vos informations");
        return new ActionFlow("users/modifier", false);
    }
}
