package servlet;

import command.ActionFlow;
import command.Command;
import command.CommandManager;
import java.io.IOException;
import java.io.PrintWriter;
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

        String ctx = getServletContext().getContextPath();
        String commandUrl = null;
        String[] UrlParams = null;
        ActionFlow flow;
        Pattern p = Pattern.compile(ctx + "/(.*)");
        Matcher m = p.matcher(request.getRequestURI());
        if ((m.find() && m.groupCount() == 1)) {
            UrlParams = m.group(1).split("/");
            commandUrl = UrlParams[0];
        }
        Command cmd = CommandManager.getCommand(commandUrl);
        flow = (cmd == null) ? new ActionFlow("error", true) : cmd.actionPerform(request, UrlParams);
        if (flow != null) {
            if (flow.isRedirect()) {
                response.sendRedirect(ctx + "/" + flow.getPath());
            } else {
                request.setAttribute("view", flow.getPath() + ".jsp");
                request.setAttribute("page", flow.getAttrPage());
                getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);
            }
        }
    }
}
