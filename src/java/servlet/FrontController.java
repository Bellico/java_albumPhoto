package servlet;

import command.ActionFlow;
import command.ICommand;
import command.CommandManager;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.Tools;

@MultipartConfig(maxFileSize = 10485760, fileSizeThreshold = 10485760, maxRequestSize = 52428800)
public class FrontController extends HttpServlet {

    private final String TEMPLATE_SERVLET = "/WEB-INF/layouts/albumphoto.jsp";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        ActionFlow flow = (ActionFlow) request.getAttribute("ACTIONFLOW");
        HashMap<Integer, String> urlParams = Tools.parseUrl(request.getRequestURI());
        String commandUrl = urlParams.get(0);

        if (flow == null) {
            ICommand cmd = CommandManager.getCommand(commandUrl);
            flow = (cmd == null) ? new ActionFlow("error", true) : cmd.actionPerform(request, urlParams);
        }
        if (flow != null) {
            if (flow.isRedirect()) {
                if (flow.getPath().startsWith("http:")) {
                    response.sendRedirect(flow.getPath());
                } else {
                    String ctx = getServletContext().getContextPath();
                    response.sendRedirect(ctx + "/" + flow.getPath());
                }

            } else {
                request.setAttribute("view", flow.getPath() + ".jsp");
                getServletContext().getRequestDispatcher(TEMPLATE_SERVLET).forward(request, response);
            }
        }
    }
}
