package servlet;

import command.Command;
import command.CommandManager;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "serv", urlPatterns = {"*.do"})
public class FrontController extends HttpServlet {

    private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandUrl = null;
        Pattern p = Pattern.compile(".*/(.*).do");
        Matcher m = p.matcher(request.getRequestURI());
        if (m.find() && m.groupCount() == 1) {
            commandUrl = m.group(1);
        }
        Command cmd = CommandManager.getCommand(commandUrl);
        if (cmd == null) {
            cmd = CommandManager.getCommand("error");
        }
        cmd.actionPerform(request);
        getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);
    }
}
