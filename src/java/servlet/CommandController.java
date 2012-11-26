package servlet;

import command.ActionFlow;
import command.Command;
import command.CommandManager;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "serv", urlPatterns = {"/do/*"})
@MultipartConfig(maxFileSize = 10485760, fileSizeThreshold = 10485760, maxRequestSize = 52428800)
public class CommandController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandUrl = null;
        ActionFlow flow;
        Pattern p = Pattern.compile(".*/do/(.*)");
        Matcher m = p.matcher(request.getRequestURI());
        if (m.find() && m.groupCount() == 1) {
            commandUrl = m.group(1);
        }
        Command cmd = CommandManager.getCommand(commandUrl);
        flow = (cmd == null) ? new ActionFlow("error", true) : cmd.actionPerform(request);
        String ctx = getServletContext().getContextPath();
        if (flow.isRedirect()) {
            response.sendRedirect(ctx + "/" + flow.getPath());
        } else {
            getServletContext().getRequestDispatcher("/" + flow.getPath()).forward(request, response);
        }
    }
}
