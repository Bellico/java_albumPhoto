package bdd;

import bean.AlbumBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AlbumMap extends FormatQuery{
    
      public AlbumMap() {
        this.table = "albums";
        this.primary_key = "idAlbum";
    }

    protected int getPrimaryKey(Object o) {
        AlbumBean album = (AlbumBean) o;
        return album.getIdAlbum();
    }

    public AlbumBean ResultToBean(ResultSet res) throws SQLException {
        return new AlbumBean(
                res.getInt("idAlbum"),
                res.getInt("idUser"),
                res.getInt("idStatut"));
    }

    HashMap<String, Object> BeanToData(Object o) {
        AlbumBean album = (AlbumBean) o;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("idAlbum", album.getIdAlbum());
        data.put("idUser", album.getIdUser());
        data.put("idStatut", album.getIdStatut());
        return data;
    }
}
