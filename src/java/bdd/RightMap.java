package bdd;

import bean.RightBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RightMap {

  /*  public RightMap() {
        this.table = "rights";
        this.primary_key = "idUser";
    }

    protected int getPrimaryKey(Object o) {
        RightBean right = (RightBean) o;
        return -1;
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
    }*/
/*
    public int save(Object o) {
         RightBean right = (RightBean) o;
        HashMap<String, Object> data = BeanToData(o);
      
            try {
                 if(right.getIdAlbum()>0 && right.getIdUser() > 0 )
        {
            prepareQuery("saveRight","update rights set ");
        }
            } catch (SQLException ex) {
                System.out.println("[ Erreur Methode Save ] : " + ex.getMessage());
                res = 0;
            }
        
    
        return res;
    }*/
}
