package command;

import bdd.AlbumMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class AlbumCommand implements Command {

    private static HashMap<String, String> attrPage = new HashMap<String, String>();

    static {
        attrPage.put("tit1ePage", "Albums");
        attrPage.put("namePage", "Liste des Albums");
    }

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
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
        return new ActionFlow("albums.jsp", attrPage, false);
    }
}
