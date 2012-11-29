package command;

import bdd.PhotoMap;
import bean.PhotoBean;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import model.Upload;
import tools.Tools;

public class UploadCommand implements Command {

    public static final String FOLDER_ALBUM = "albums/"  ;

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        Upload up = new Upload("file", new String[]{"jpg", "jpeg", "png"});
         String album = request.getParameter("album");
        try {
            String path = Tools.appPath + File.separator + FOLDER_ALBUM  + album;
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
                photo.setIdAlbum(1);
                PhotoMap map = new PhotoMap();
                map.save(photo);
            }
        } catch (Exception ex) {
             System.out.println("[ Erreur Command Upload] : " + ex.getMessage());
        }
        return new ActionFlow("upload", true);
    }
}
