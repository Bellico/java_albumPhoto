package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class AlbumCommand extends Command {

    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();
    private PhotoMap mapPhoto = new PhotoMap();

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {
        try {
            Integer numphoto = Integer.parseInt(UrlParams[1]);
            return detailsAlbum(request, numphoto);
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
                        Integer.toString(al.getIdAlbum()),
                        Integer.toString(al.getNbPhoto())
                    });
        }
        request.setAttribute("listAlbum", tab);

        setAttrPage(TITRE_PAGE, "Albums");
        setAttrPage(NOM_PAGE, "Liste des Albums");
        return new ActionFlow("albums", attrPage, false);
    }

    public ActionFlow detailsAlbum(HttpServletRequest request, int numalbum) {
        AlbumBean album = (AlbumBean) mapAlbum.getbyId(numalbum);
        if (album == null) {
            request.setAttribute(ErrorCommand.MESSAGE_ERROR, "Cet album n'existe pas.");
            return new ActionFlow("error", attrPage, false);
        }


        UserBean user = (UserBean) mapUser.getbyId(album.getIdUser());
        String[] tab = new String[]{
            user.getName() + " " + user.getFirstName(),
            album.getNameAlbum(),
            album.getDescr(),
            Integer.toString(album.getNbPhoto())};

        request.setAttribute("details", tab);

        ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idalbum", album.getIdAlbum());
        ArrayList<String[]> tab2 = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {

            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("IDALBUM", al.getIdAlbum());
            for (PhotoBean ph : photos) {
                tab2.add(new String[]{
                            UploadCommand.FOLDER_ALBUM + al.getNameAlbum() + "/" + ph.getImg(),
                            user.getName() + " " + user.getFirstName(),
                            al.getNameAlbum(),
                            ph.getTitle(),
                            ph.getDescr(),
                            ph.getDateCreated(),
                            ph.getDateLastUpdate(),
                            Integer.toString(ph.getIdPhoto())
                        });
            }
        }
        request.setAttribute("listImg", tab2);

        setAttrPage(TITRE_PAGE, "Détails Album");
        setAttrPage(NOM_PAGE, "Détails de l'album");
        return new ActionFlow("detailsAlbum", attrPage, false);
    }
}
