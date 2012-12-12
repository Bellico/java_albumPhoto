package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bean.AlbumBean;
import bean.PhotoBean;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import tools.Tools;

public class DefaultCommand implements ICommand {

    private PhotoMap mapPhoto = new PhotoMap();
    private AlbumMap mapAlbum = new AlbumMap();

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, HashMap urlParams) {

        ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idStatut", 0);
        ArrayList<String[]> tab = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", al.getIdAlbum());
            String albumCrypt = Tools.crypt(al.getNameAlbum(), Tools.SHA1, true).replace("/", "").replace("=", "");
            for (PhotoBean ph : photos) {
                tab.add(new String[]{
                            UploadCommand.FOLDER_ALBUM + "/" + albumCrypt + "/" + ph.getImg(),
                            ph.getTitle(),
                            ph.getDescr(),});
            }
        }
        
        request.setAttribute(TITRE_PAGE, "Accueil");
        request.setAttribute(NOM_PAGE, "Accueil");
        request.setAttribute("listImg", tab);
        return new ActionFlow("index", false);
    }
}