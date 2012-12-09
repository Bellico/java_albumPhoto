package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    /**
     * Constructeur
     *
     * @param formFiel : Nom du champs du formulaire permettant de charger un
     * fichier
     * @param etx : extensions acceptées pour l'upload
     */
    public Upload(String formFiel, String[] etx) {
        this.form_field = formFiel;
        this.ext = etx;
    }

    public String getFileName() {
        return fileName;
    }

/**
     * Récupere le fichier binaire dans la requete , et l'enregistre
     *
     * @param request
     * @param path : cible de lieu d'enregistrement
     * @return entier déterminant l'état de la fonction
     *      0 : Tous s'est bien passé 
     *      1 : Aucun fichier n'est présent dans la requete 
     *      2 : Le taille du fichier est supérieure à celle attendue 
     *      3 : Erreur coté serveur 
     *      4 : Erreur au niveau de l'écriture
     *      5 : Le fichier n'est pas du type attendu
     */
    public int uploadFile(HttpServletRequest request, String path) {
        int state = 0;
        InputStream content = null;
        try {
            Part part = request.getPart(form_field);
            fileName = getNameFromHeader(part);
            if (fileName != null && !fileName.isEmpty()) {
                fileName = fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (ext != null) {
                    if (Tools.in_Array(extension.toLowerCase(), ext)) {
                        content = part.getInputStream();
                    } else {
                        state = 5;
                    }
                } else {
                    content = part.getInputStream();
                }
                if (state == 0) {
                    try {
                        fileName = Tools.crypt(fileName, Tools.SHA1, false).replace("/", "").replace("=", "") + "." + extension;
                        writeFile(content, fileName, path);
                    } catch (Exception ex) {
                        state = 4;
                        System.out.println("[ Erreur Upload Ecriture Fichier ] : " + ex.getMessage());
                    }
                }
                //Aucun fichier sélectionnée
            } else {
                state = 1;
            }
            //Données trop volumineuses
        } catch (IllegalStateException e) {
            state = 2;
            //Erreures potentielles = IOException - ServletException - RuntimeException
        } catch (Exception ex) {
            state = 3;
            System.out.println("[ Erreur Serveur Upload Fichier ] : " + ex.getMessage());
        }
        return state;
    }

    /*
     * Analyser l'en-tête "content-disposition", vérifier le paramètre "filename" et renvoi le nom de fichier 
     */
    private static String getNameFromHeader(Part part) {
        // Boucle sur chacun des paramètres de l'en-tête "content-disposition"
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
            //Recherche de la présence du paramètre "filename"
            if (contentDisposition.trim().startsWith("filename")) {
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
