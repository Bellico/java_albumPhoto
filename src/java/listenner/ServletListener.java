package listenner;

import bdd.Database;
import command.CommandManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import tools.Tools;

@WebListener()
public class ServletListener implements ServletContextListener {

    private Database BDctx;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Initialisation de la connexion a la base de donnée
        BDctx = new Database(sce.getServletContext());
        sce.getServletContext().setAttribute("bdd", BDctx);
        //Initialisation des commandes
        CommandManager.init();
        Tools.appPath=sce.getServletContext().getRealPath("");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        BDctx.close();
    }

}
