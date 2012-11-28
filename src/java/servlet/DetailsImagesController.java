/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author liodau1u
 */

@WebServlet(name = "DetailsImagesController", urlPatterns = {"/details"})
public class DetailsImagesController extends HttpServlet {
     private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("view", "detailsimages.jsp");
        request.setAttribute("nompage", "Détail de l'image");
        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);

    }
    
    
}
