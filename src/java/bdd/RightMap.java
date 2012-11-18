/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import bean.RightBean;
import bean.UserBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Franck
 */
public class RightMap extends FormatQuery{
     public RightMap() {
        this.table = "rights";
        this.primary_key = "idUser";
    }

    protected int getPrimaryKey(Object o) {
        RightBean right = (RightBean) o;
        return right.getIdUser();
    }

    public RightBean ResultToBean(ResultSet res) throws SQLException {
        return new RightBean(
                res.getInt("idUser"),
                res.getInt("idAlbum"),
                res.getBoolean("lire"),
                res.getBoolean("inserer"),
                res.getBoolean("modifier"),
                res.getBoolean("supprimer"));
    }

    HashMap<String, Object> BeanToData(Object o) {
        RightBean right = (RightBean) o;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("lire", right.isLire());
        data.put("inserer", right.isInserer());
        data.put("modifier", right.isModifier());
        data.put("supprimer", right.isSupprimer());
        return data;
    }
}
