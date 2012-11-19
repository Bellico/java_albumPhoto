package servlet;

import command.Command;
import command.CommandManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "serv", urlPatterns = {"/"})
public class FrontController extends HttpServlet {

    private final String TEMPLATE_SERVLET = "/WEB-INF/templates/albumphoto.jsp";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandUrl = request.getRequestURI().replace(getServletContext().getContextPath() + "/", "");
        Command cmd = CommandManager.getCommand(commandUrl);
        if (!commandUrl.equals("")) {
            if (cmd == null) {
                cmd = CommandManager.getCommand("error");
            }
        } else {
            cmd = CommandManager.getCommand("default");
        }
        cmd.actionPerform(request);
        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);
    }
}
