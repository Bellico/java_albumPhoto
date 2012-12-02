package command;

import bdd.AlbumMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import model.ControlForm;
import tools.Tools;

public class AlbumCommand extends Command {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        try {
            if (UrlParams[1].equals("nouveau")) {
                return newAlbum(request);
            } else {
                Integer numphoto = Integer.parseInt(UrlParams[1]);
                return null; //details
            }
        } catch (IndexOutOfBoundsException ex) {
            return listAlbum(request);
        } catch (NumberFormatException ex) {
            return new ActionFlow("error", true);
        }
    }

    public ActionFlow listAlbum(HttpServletRequest request) {
        AlbumMap mapAlbum = new AlbumMap();
        UserMap mapUser = new UserMap();
        ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idStatut", 0);
        ArrayList<String[]> tab = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {
            UserBean user = (UserBean) mapUser.getbyAttr("idUser", al.getIdUser());
            tab.add(new String[]{
                        al.getNameAlbum(),
                        user.getName() + " " + user.getFirstName(),
                        al.getDescr(),
                        Integer.toString(al.getNbPhoto())
                    });
        }
        request.setAttribute("listAlbum", tab);

        setAttrPage(TITRE_PAGE, "Albums");
        setAttrPage(NOM_PAGE, "Liste des Albums");
        return new ActionFlow("albums", attrPage, false);
    }

    public ActionFlow newAlbum(HttpServletRequest request) {
        ControlForm form = new ControlForm(request);
        String name = form.check("name", ControlForm.NON_VIDE, "Donnez un titre à votre album");
        String description = form.check("description", ControlForm.NON_VIDE, "Une petite description ?");
        form.close();
        if (form.getNbError() == 0) {
            AlbumMap map = new AlbumMap();
            AlbumBean album = new AlbumBean();
            album.setNameAlbum(name);
            album.setDescr(description);
            album.setIdStatut(0);
            album.setIdUser(1);
            if (map.save(album) == 1) {
                setAttrPage(MESSAGE, "Album ajouté!");
            } else {
                setAttrPage(MESSAGE, "L'incription ne s'est pas terminée correctement, une erreur serveur s'est produite");
            }

        }
        setAttrPage(TITRE_PAGE, "Albums");
        setAttrPage(NOM_PAGE, "Creer un Album");
        return new ActionFlow("ajoutAlbum", attrPage, false);

    }
}
