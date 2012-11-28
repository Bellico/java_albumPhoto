package servlet;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.Tools;

@WebServlet(name = "ImagesCOntroller", urlPatterns = {"/images"})
public class ImagesController extends HttpServlet {

    private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("view", "images.jsp");

        PhotoMap mapPhoto = new PhotoMap();
        AlbumMap mapAlbum = new AlbumMap();
        UserMap mapUser = new UserMap();

        ArrayList<AlbumBean> albumpublic = mapAlbum.getAllbyAttr("idStatut", 0);


        int i = 0;
        int j = 0;
        //  Object[][] tab ;
        ArrayList<String[]> tab = new ArrayList<String[]>();
        for (AlbumBean al : albumpublic) {
            UserBean user = (UserBean) mapUser.getbyAttr("idUser", al.getIdUser());
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", al.getIdAlbum());
            for (PhotoBean ph : photos) {
                tab.add(new String[]{
                            ph.getImg(),
                            user.getName(),
                            al.getNameAlbum(),
                            ph.getTitle(),
                            ph.getDescr(),
                            Tools.DateToString(ph.getDate_created(), ph.getTime_created()),
                            Tools.DateToString(ph.getDate_lastUpdate(), ph.getTime_lastUpdate())
                         
                        });
            }

        }




        /*
         * 
         Database db = new Database();
         String query = "SELECT title,photos.descr,photos.date_created,photos.time_created,photos.date_lastUpdate,photos.time_lastUpdate,nameAlbum ,name"
         + "FROM users,albums,photos"
         + "WHERE albums.idAlbum=photos.idAlbum  AND  albums.idUser=users.idUser AND idStatut=0"
         + "ORDER BY photos.date_lastUpdate DESC,photos.time_lastUpdate DESC, photos.date_created DESC,photos.time_created DESC";

         PreparedStatement statement = db.prepareQuery("listphoto", query);

         try {
         //statement.setString(1, null);
         ResultSet res = statement.executeQuery();
         while (res.next()) {
         }
         } catch (SQLException ex) {
         }
         */
        request.setAttribute("listImg", tab);
        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);

    }
}
