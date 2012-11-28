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

    @Override
    public ActionFlow actionPerform(HttpServletRequest request) {
        Upload up = new Upload("file", new String[]{"jpg", "jpeg", "png"});
        try {
            String path = Tools.appPath + File.separator + "img";
            int state = up.uploadFile(request, path);
            if (state == 0) {
                Image img = ImageIO.read(new File(path + File.separator + up.getFileName()));
                PhotoBean photo = new PhotoBean();
                photo.setTitle("Mon petit chat");
                photo.setDescr("Je te décrit");
                photo.setImg(up.getFileName());
                photo.setHeight(img.getHeight(null));
                photo.setWidth(img.getWidth(null));
                photo.setKeyword(Tools.generate_KeyWord(photo.getDescr()));
                photo.setIdAlbum(1);
                PhotoMap map = new PhotoMap();
                map.save(photo);
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return new ActionFlow("upload", true);
    }
}
