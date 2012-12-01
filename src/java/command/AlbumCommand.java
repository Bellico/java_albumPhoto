package command;

import bdd.AlbumMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class AlbumCommand extends Command {

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
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
}
