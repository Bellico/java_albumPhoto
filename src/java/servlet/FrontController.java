package servlet;

import command.ActionFlow;
import command.ICommand;
import command.CommandManager;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 10485760, fileSizeThreshold = 10485760, maxRequestSize = 52428800)
public class FrontController extends HttpServlet {

    private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String ctx = getServletContext().getContextPath();
        String commandUrl = null;
        String[] urlParams = null;
        ActionFlow flow;
        
        Pattern p = Pattern.compile(ctx + "/(.*)");
        Matcher m = p.matcher(request.getRequestURI());
        if ((m.find() && m.groupCount() == 1)) {
            urlParams = m.group(1).split("/");
            commandUrl = urlParams[0];
        }
        
        ICommand cmd = CommandManager.getCommand(commandUrl);
        flow = (cmd == null) ? new ActionFlow("error", true) : cmd.actionPerform(request, urlParams);
        if (flow != null) {
            if (flow.isRedirect()) {
                response.sendRedirect(ctx + "/" + flow.getPath());
            } else {
                request.setAttribute("view", flow.getPath() + ".jsp");
                getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);
            }
        }
    }
}
