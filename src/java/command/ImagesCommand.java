package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import tools.Tools;

public class ImagesCommand implements Command {

    private static HashMap<String, String> attrPage = new HashMap<String, String>();

    static {
        attrPage.put("tit1ePage", "Images");
        attrPage.put("namePage", "Liste des images");
    }

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        PhotoMap mapPhoto = new PhotoMap();
        AlbumMap mapAlbum = new AlbumMap();
        UserMap mapUser = new UserMap();

        ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idStatut", 0);

        ArrayList<String[]> tab = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {
            UserBean user = (UserBean) mapUser.getbyAttr("idUser", al.getIdUser());
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", al.getIdAlbum());
            for (PhotoBean ph : photos) {
                tab.add(new String[]{
                            UploadCommand.FOLDER_ALBUM + al.getNameAlbum() + "/" + ph.getImg(),
                            user.getName(),
                            al.getNameAlbum(),
                            ph.getTitle(),
                            ph.getDescr(),
                            Tools.DateToString(ph.getDate_created(), ph.getTime_created()),
                            Tools.DateToString(ph.getDate_lastUpdate(), ph.getTime_lastUpdate()),
                            Integer.toString(ph.getIdPhoto())
                        });
            }
        }

        request.setAttribute("listImg", tab);
        return new ActionFlow("images.jsp",attrPage, false);
    }
}

/*
 *   Integer numphoto = Integer.parseInt(request.getParameter("nrophoto"));

        PhotoMap mapPhoto = new PhotoMap();
        AlbumMap mapAlbum = new AlbumMap();
        UserMap mapUser = new UserMap();

        PhotoBean photo = (PhotoBean) mapPhoto.getbyId(numphoto);
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(photo.getIdAlbum());
        UserBean user = (UserBean) mapUser.getbyId(album.getIdUser());
        System.out.print(photo.getIdPhoto());
        String[] tab = new String[]{
            UploadCommand.FOLDER_ALBUM + album.getNameAlbum() + "/" + photo.getImg(),
            user.getName(),
            album.getNameAlbum(),
            photo.getTitle(),
            photo.getDescr(),
            Integer.toString(photo.getWidth()),
            Integer.toString(photo.getHeight()),
            Tools.DateToString(photo.getDate_created(), photo.getTime_created()),
            Tools.DateToString(photo.getDate_lastUpdate(), photo.getTime_lastUpdate())
        };
         request.setAttribute("details", tab);
 */