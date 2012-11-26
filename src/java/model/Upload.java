package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import tools.Tools;

/**
 * Permet d'uploader un fichier Utilisation de la librairie :
 * http://commons.apache.org/fileupload/ Inspiré de :
 * http://www.siteduzero.com/tutoriel-3-682903-integration-dans-mvc.html
 */
public class Upload {

    private String form_field = "file";
    private String[] ext;
    private String fileName = null;
    private static final int TAMPON = 10240;

    public Upload(String formFiel, String[] etx) {
        this.form_field = formFiel;
        this.ext = etx;
    }

    public String getFileName() {
        return fileName;
    }

    public int uploadFile(HttpServletRequest request, String path) throws Exception {
        int state = 0;
        InputStream content = null;
        try {
            Part part = request.getPart(form_field);
            fileName = getNameFromHeader(part);
            if (fileName != null && !fileName.isEmpty()) {
                fileName = fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
                if (ext != null) {
                    String ex = fileName.substring(fileName.lastIndexOf(".") + 1);
                    if (Tools.in_Array(ex, ext)) {
                        content = part.getInputStream();
                    }
                } else {
                    content = part.getInputStream();
                }
                //Aucun fichier sélectionnée
                if (fileName == null || content == null) {
                    state = 1;
                } else {
                    try {
                        fileName = Tools.crypt(fileName, Tools.SHA1, false).replace("/", "");
                        writeFile(content, fileName, path);
                    } catch (Exception ex) {
                        state = 4;
                        System.out.println("[ Erreur Upload Fichier ] : " + ex.getMessage());
                    }
                }
            }
            //Données trop volumineuses
        } catch (IllegalStateException e) {

            state = 2;
        } catch (IOException ex) {
            System.out.println("[ Erreur Upload Fichier ] : " + ex.getMessage());
            state = 3;
        }
        return state;
    }

    /*
     * Analyser l'en-tête "content-disposition", et de vérifier le paramètre "filename"
     */
    private static String getNameFromHeader(Part part) {
        // Boucle sur chacun des paramètres de l'en-tête "content-disposition"
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
            //Recherche de la présence du paramètre "filename"
            if (contentDisposition.trim().startsWith("filename")) {
                //Renvoi du nom de fichier 
                return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    /**
     * Ecrit un fichier
     */
    private void writeFile(InputStream content, String nameFile, String path) throws Exception {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(content, TAMPON);
            out = new BufferedOutputStream(new FileOutputStream(new File(path + File.separator + nameFile)), TAMPON);
            byte[] tampon = new byte[TAMPON];
            int lenght = 0;
            while ((lenght = in.read(tampon)) > 0) {
                out.write(tampon, 0, lenght);
            }
        } finally {
            out.close();
            in.close();
        }
    }
}
