package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "uploadController", urlPatterns = {"/upload"})
public class UploadController extends HttpServlet {
  private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("view", "up.jsp");
        request.setAttribute("nompage", "Upload d'image");
        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);

    }
}
