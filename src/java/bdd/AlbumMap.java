package bdd;

import bean.AlbumBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AlbumMap extends SQLMapping {

    public AlbumMap() {
        this.table = "albums";
        this.primary_key = "idAlbum";
    }

    @Override
    protected int getPrimaryKey(Object o) {
        AlbumBean album = (AlbumBean) o;
        return album.getIdAlbum();
    }

    @Override
    public AlbumBean ResultToBean(ResultSet res) throws SQLException {
        return new AlbumBean(
                res.getInt("idAlbum"),
                res.getString("nameAlbum"),
                res.getString("descr"),
                res.getInt("idUser"),
                res.getInt("idStatut"));
    }

    @Override
    public HashMap<String, Object> BeanToData(Object o) {
        AlbumBean album = (AlbumBean) o;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("idUser", album.getIdUser());
        data.put("nameAlbum", album.getNameAlbum());
        data.put("descr", album.getDescr());
        data.put("idStatut", album.getIdStatut());
        return data;
    }
}
