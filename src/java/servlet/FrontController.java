package servlet;

import command.ActionFlow;
import command.Command;
import command.CommandManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.Tools;

@WebServlet(name = "serv", urlPatterns = {"/*"})
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ActionFlow flow;
       
        String operation =  request.getRequestURI().replace(getServletContext().getContextPath()+"/", "");
    
        
        String gg = "Une pe:tite desct:iion bien gr?ose, lolilou";
        PrintWriter o = response.getWriter();
        o.print(Tools.generate_KeyWord(gg));
      /*  CommandManager.init();
        Command cmd = CommandManager.getCommand(operation);*/
/*
        if (cmd != null) {
            flow = cmd.actionPerform(request);
        } else {
            flow = null;
        }
        if (flow.isRedirect()) {
            response.sendRedirect(flow.getPath());
        } else {
            getServletContext().getRequestDispatcher(flow.getPath()).forward(request, response);
        }*/
    }
}
