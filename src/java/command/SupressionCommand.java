/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.RightMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.RightBean;
import bean.UserBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emilien
 */
public class SupressionCommand implements ICommand {

    private PhotoMap mapPhoto = new PhotoMap();
    private AlbumMap mapAlbum = new AlbumMap();
    private UserMap mapUser = new UserMap();
    private RightMap mapRight = new RightMap();

    @Override
    public ActionFlow actionPerform(HttpServletRequest request, String[] UrlParams) {

        if (UrlParams[1].equals("deletePhoto")) {
            return PhotoDelete(request, Integer.parseInt(UrlParams[2]));
        } else if (UrlParams[1].equals("deleteAlbum")) {
            return AlbumDelete(request, Integer.parseInt(UrlParams[2]));
        } else {
            return new ActionFlow("index", true);
        }

    }

    public ActionFlow PhotoDelete(HttpServletRequest request, int numphoto) {

        PhotoBean photo = (PhotoBean) mapPhoto.getbyId(numphoto);

        mapPhoto.delete(photo);


        return new ActionFlow("index", false);
    }

    public ActionFlow AlbumDelete(HttpServletRequest request, int albumnum) {


        AlbumBean album = (AlbumBean) mapAlbum.getbyId(albumnum);
        UserBean user = (UserBean) mapUser.getbyAttr("idUser", album.getIdUser());
        ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", album.getIdAlbum());
        for (PhotoBean ph : photos) {
            mapPhoto.delete(ph);
        }


        mapAlbum.delete(album);


        return new ActionFlow("index", false);
    }
}
