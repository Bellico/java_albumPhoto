package servlet;

import bdd.AlbumMap;
import bdd.PhotoMap;
import bean.AlbumBean;
import bean.PhotoBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ImagesCOntroller", urlPatterns = {"/images"})
public class ImagesController extends HttpServlet {

    private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("view", "images.jsp");
        HashMap<AlbumBean, PhotoBean> ap = new   HashMap<AlbumBean, PhotoBean>();
        
        ArrayList<PhotoBean> listPhoto = new ArrayList<PhotoBean>();
        PhotoMap mapPhoto = new PhotoMap();
        AlbumMap mapAlbum = new AlbumMap();
        ArrayList<AlbumBean> listAlbum = mapAlbum.getAllbyAttr("idStatut", 0);
         request.setAttribute("listAlbum", listAlbum);
         
        for (AlbumBean al : listAlbum) {
            listPhoto.add((PhotoBean) mapPhoto.getbyAttr("idAlbum", al.getIdAlbum()));
        }
        request.setAttribute("listPhoto", listPhoto);

        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);

    }
}
