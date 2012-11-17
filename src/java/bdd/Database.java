package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.servlet.ServletContext;

public class Database {

    private String url = "urlBasedeDonnee";
    private String login = "nomUtilisateur";
    private String password = "motdepass";
    private static Connection connexion = null;
    private static HashMap<String, PreparedStatement> listQueryPrepare = new HashMap<String, PreparedStatement>();

    public Database(ServletContext ctx) {
        url = initWithServletContext(url, "databaseUrl", ctx);
        login = initWithServletContext(login, "databaseUser", ctx);
        password = initWithServletContext(password, "databasePassword", ctx);
        connect();
    }

    public Database(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
        connect();
    }

    public Database() {
        connect();
    }

    public void close() {
        if (connexion != null) {
            try {
                connexion.close();
                connexion = null;
            } catch (SQLException ex) {
                System.out.println("[ Erreur Fermeture BDD ] : " + ex.getMessage());
            }
        }
    }

    synchronized public ResultSet query(String sql) {
        try {
            Statement statement = connexion.createStatement();
            ResultSet res = statement.executeQuery(sql);
            return res;
        } catch (SQLException ex) {
            System.out.println("[ Erreur Requete SQL ] : " + ex.getMessage());
            return null;
        }
    }

    synchronized public int update(String sql) {
        try {
            Statement statement = connexion.createStatement();
            int res = statement.executeUpdate(sql);
            return res;
        } catch (SQLException ex) {
            System.out.println("[ Erreur Requete SQL ] : " + ex.getMessage());
            return 0;
        }
    }

    synchronized public PreparedStatement prepareQuery(String nameStatement, String sql) {
        try {
            PreparedStatement statement = listQueryPrepare.get(nameStatement);
            if (statement != null) {
                return statement;
            } else {
                statement = connexion.prepareStatement(sql);
                listQueryPrepare.put(nameStatement, statement);
                return statement;
            }
        } catch (SQLException ex) {
            System.out.println("[ Erreur Requete SQL ] : " + ex.getMessage());
            return null;
        }

    }

    private void connect() {
        if (connexion == null) {
            try {
                connexion = DriverManager.getConnection(url, login, password);
            } catch (SQLException ex) {
                System.out.println("[ Erreur Connexion BDD ] : " + ex.getMessage());
            }
        }
    }

    private String initWithServletContext(String defaultValue, String params, ServletContext ctx) {
        if (ctx.getInitParameter(params) != null) {
            return ctx.getInitParameter(params);
        } else {
            return defaultValue;
        }
    }
}