/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bdd.AlbumMap;
import bdd.UserMap;
import bean.UserBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emilien
 */
@WebServlet(name = "UtilisateursController", urlPatterns = {"/utilisateurs"})
public class UtilisateursController extends HttpServlet {
    
      private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("view", "utilisateurs.jsp");


   UserMap p = new UserMap();
   ArrayList<UserBean> tab= p.getAll();
   request.setAttribute("User", tab);
    
   
        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);

    }
    
}
