package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import tools.Tools;

public class PhotosCommand extends Command {

    private PhotoMap mapPhoto = new PhotoMap();
    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        try {
            Integer numphoto = Integer.parseInt(UrlParams[1]);
            return detailsPhoto(request, numphoto);
        } catch (IndexOutOfBoundsException ex) {
            return listPhoto(request);
        } catch (NumberFormatException ex) {
            return new ActionFlow("error", true);
        }
    }

    public ActionFlow listPhoto(HttpServletRequest request) {
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

        setAttrPage(TITRE_PAGE, "Photos");
        setAttrPage(NOM_PAGE, "Liste des photos");
        return new ActionFlow("photos", attrPage, false);
    }

    public ActionFlow detailsPhoto(HttpServletRequest request, int numphoto) {
        PhotoBean photo = (PhotoBean) mapPhoto.getbyId(numphoto);
        if(photo==null){ 
            request.setAttribute(ErrorCommand.MESSAGE_ERROR,"Cette photo n'existe pas.");
            return new ActionFlow("error",attrPage,false);
        }
        
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(photo.getIdAlbum());
        UserBean user = (UserBean) mapUser.getbyId(album.getIdUser());
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

        setAttrPage(TITRE_PAGE, "Details Photos");
        setAttrPage(NOM_PAGE, "Details de la photo");
        return new ActionFlow("detailsPhoto", attrPage, false);
    }
}
