package bdd;

import bean.RightBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RightMap extends SQLMapping {

    public RightMap() {
        this.table = "rights";
        this.primary_key = "idAlbum";

    }

    @Override
    protected int getPrimaryKey(Object o) {
        RightBean right = (RightBean) o;
        return right.getIdAlbum();
    }

    @Override
    public RightBean ResultToBean(ResultSet res) throws SQLException {
        return new RightBean(
                res.getInt("idUser"),
                res.getInt("idAlbum"),
                res.getBoolean("lire"),
                res.getBoolean("inserer"),
                res.getBoolean("modifier"),
                res.getBoolean("supprimer"));
    }

    @Override
    public HashMap<String, Object> BeanToData(Object o) {
        RightBean right = (RightBean) o;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("idUser", right.getIdUser());
        data.put("idAlbum", right.getIdAlbum());
        data.put("lire", right.isLire());
        data.put("inserer", right.isInserer());
        data.put("modifier", right.isModifier());
        data.put("supprimer", right.isSupprimer());
        return data;
    }

    public RightBean get(int idUser, int idAlbum) {
        String nameStatement = "getRight";
        PreparedStatement statement = prepareQuery(nameStatement, "select * from " + table + " where idUser = ? and idAlbum = ?");
        try {
            setStatement(statement, idUser, 1);
            setStatement(statement, idAlbum, 2);
            ResultSet res = statement.executeQuery();
            res.next();
            return ResultToBean(res);
        } catch (Exception ex) {
            return null;
        }
    }

    public int save(RightBean r) {
        PreparedStatement statement;
        if (get(r.getIdUser(), r.getIdAlbum()) == null) {
            statement = prepareQuery("insertRight", "insert into rights (lire,inserer,modifier,supprimer,idUser,idAlbum) values (?,?,?,?,?,?)");
        } else {
            statement = prepareQuery("updateRight", "update rights set lire=?,inserer=?,modifier=?,supprimer=? where idUser=? and idAlbum=?");
        }
        try {
            setStatement(statement, r.isLire(), 1);
            setStatement(statement, r.isInserer(), 2);
            setStatement(statement, r.isModifier(), 3);
            setStatement(statement, r.isSupprimer(), 4);
            setStatement(statement, r.getIdUser(), 5);
            setStatement(statement, r.getIdAlbum(), 6);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("[ Erreur Methode Save ] : " + ex.getMessage());
            return 0;
        }
    }

    public int delete(int idUser, int idAlbum) {
        PreparedStatement statement = prepareQuery("deleteRight", "delete from " + table + " where idUser=? and idAlbum=?");
        try {
            setStatement(statement, idUser, 1);
            setStatement(statement, idAlbum, 2);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("[ Erreur Methode Delete ] : " + ex.getMessage());
            return 0;
        }
    }
    
}
