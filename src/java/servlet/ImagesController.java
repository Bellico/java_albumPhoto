package servlet;

import java.io.IOException;
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
        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);

    }
}