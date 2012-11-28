package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlbumsController", urlPatterns = {"/albums"})
public class AlbumsController extends HttpServlet {
  private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("view", "albums.jsp");
         request.setAttribute("nompage", "Liste des Albums");
        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);

    }
}
