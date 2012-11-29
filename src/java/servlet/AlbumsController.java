package servlet;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bdd.UserMap;
import bean.AlbumBean;
import bean.PhotoBean;
import bean.UserBean;
import command.UploadCommand;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.Tools;

@WebServlet(name = "AlbumsController", urlPatterns = {"/albums"})
public class AlbumsController extends HttpServlet {

    private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("view", "albums.jsp");
        request.setAttribute("nompage", "Liste des Albums");

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
                        Integer.toString(al.getNbPhoto())
                    });
        }
       
         request.setAttribute("listAlbum", tab);
        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);

    }
}
