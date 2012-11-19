package listenner;

import bdd.Database;
import command.CommandManager;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ServletListener implements ServletContextListener, ServletContextAttributeListener {

    private Database BDctx;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Initialisation de la connexion a la base de donnée
        BDctx = new Database(sce.getServletContext());
        sce.getServletContext().setAttribute("bdd", BDctx);
        //Initialisation des commandes
        CommandManager.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        BDctx.close();
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
    }
}
