package bdd;

import bean.PhotoBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PhotoMap extends SQLMapping {

    public PhotoMap() {
        this.table = "photos";
        this.primary_key = "idPhoto";
    }

    @Override
    protected int getPrimaryKey(Object o) {
        PhotoBean photo = (PhotoBean) o;
        return photo.getIdPhoto();
    }

    @Override
    public PhotoBean ResultToBean(ResultSet res) throws SQLException {
        return new PhotoBean(
                res.getInt("idPhoto"),
                res.getString("title"),
                res.getString("descr"),
                res.getInt("width"),
                res.getInt("height"),
                res.getString("img"),
                res.getString("keyword"),
                res.getDate("date_created"),
                res.getTime("time_created"),
                res.getDate("date_lastUpdate"),
                res.getTime("time_lastUpdate"),
                res.getInt("idAlbum"));
    }

    @Override
    public HashMap<String, Object> BeanToData(Object o) {
        PhotoBean photo = (PhotoBean) o;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("title", photo.getTitle());
        data.put("descr", photo.getDescr());
        data.put("width", photo.getWidth());
        data.put("height", photo.getHeight());
        data.put("img", photo.getImg());
        data.put("keyword", photo.getKeyword());
        data.put("date_created", photo.getDate_created());
        data.put("time_created", photo.getTime_created());
        data.put("date_lastUpdate", photo.getDate_lastUpdate());
        data.put("time_lastUpdate", photo.getTime_lastUpdate());
        data.put("idAlbum ", photo.getIdAlbum());
        return data;
    }
}
