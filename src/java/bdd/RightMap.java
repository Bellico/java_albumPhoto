package bdd;

import bean.RightBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RightMap extends SQLMapping {

    public RightMap() {
        this.table = "rights";
        this.primary_key = "idUser";

    }

    @Override
    protected int getPrimaryKey(Object o) {
        RightBean right = (RightBean) o;
        return right.getIdUser();
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


        // if(right.getIdAlbum()>0 && right.getIdUser() > 0 )

        // prepareQuery("Right","update rights set ");
        PreparedStatement statement = prepareQuery("insertRight", "insert into rights (idUser,idAlbum,lire,inserer,modifier,supprimer) values (?,?,?,?,?,?)");
        try {
            setStatement(statement, r.getIdUser(), 1);
            setStatement(statement, r.getIdAlbum(), 2);
            setStatement(statement, r.isLire(), 3);
            setStatement(statement, r.isInserer(), 4);
            setStatement(statement, r.isModifier(), 5);
            setStatement(statement, r.isSupprimer(), 6);
            return statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("[ Erreur Methode Save ] : " + ex.getMessage());
        }

        // System.out.println("[ Erreur Methode Save ] : " + ex.getMessage());

        return 0;
    }
}
