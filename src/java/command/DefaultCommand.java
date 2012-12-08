package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements ICommand {
    
        private PhotoMap mapPhoto = new PhotoMap();
    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        
                ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idStatut", 0);
        ArrayList<String[]> tab = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {
            UserBean user = (UserBean) mapUser.getbyAttr("idUser", al.getIdUser());
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", al.getIdAlbum());
            for (PhotoBean ph : photos) {
                tab.add(new String[]{
                            UploadCommand.FOLDER_ALBUM + al.getNameAlbum() + "/" + ph.getImg(),
                            user.getName() + " " + user.getFirstName(),
                            al.getNameAlbum(),
                            ph.getTitle(),
                            ph.getDescr(),
                            ph.getDateCreated(),
                            ph.getDateLastUpdate(),
                            Integer.toString(ph.getIdPhoto()),
                            Integer.toString(al.getIdAlbum())
                        });
            }
        }
        request.setAttribute("listImg", tab);
        
        request.setAttribute(TITRE_PAGE, "Accueil");
        request.setAttribute(NOM_PAGE, "Accueil");
        return new ActionFlow("index", false);
    }
}
