package command;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bean.AlbumBean;
import bean.PhotoBean;
import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import model.Upload;
import tools.Tools;

public class UploadCommand implements Command {

    private static HashMap<String, String> attrPage = new HashMap<String, String>();

    static {
        attrPage.put("tit1ePage", "Ajouter une photo");
        attrPage.put("namePage", "Photo");
    }
    public static final String FOLDER_ALBUM = "albumphoto/";

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        Upload up = new Upload("file", new String[]{"jpg", "jpeg", "png"});
        String namealbum = request.getParameter("album");
        try {
            String path = Tools.appPath + File.separator + FOLDER_ALBUM + namealbum;
            File f = new File(path);
            f.mkdirs();
            int state = up.uploadFile(request, path);
            if (state == 0) {
                String title = request.getParameter("titre");
                String descr = request.getParameter("description");
                Image img = ImageIO.read(new File(path + File.separator + up.getFileName()));
                PhotoBean photo = new PhotoBean();
                photo.setTitle(title);
                photo.setDescr(descr);
                photo.setImg(up.getFileName());
                photo.setHeight(img.getHeight(null));
                photo.setWidth(img.getWidth(null));
                photo.setKeyword(Tools.generate_KeyWord(photo.getDescr()));
                AlbumMap mapalbum = new AlbumMap();
                AlbumBean album = (AlbumBean) mapalbum.getbyAttr("nameAlbum", namealbum);
                photo.setIdAlbum(album.getIdAlbum());
                PhotoMap maphoto = new PhotoMap();
                maphoto.save(photo);
            }
        } catch (Exception ex) {
            System.out.println("[ Erreur Command Upload] : " + ex.getMessage());
        }
        return new ActionFlow("upload", true);
    }
}
