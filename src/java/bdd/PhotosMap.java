package bdd;

import bean.PhotosBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PhotosMap extends FormatQuery {

    public PhotosMap() {
        this.table = "photos";
        this.primary_key = "idPhoto";
    }

    public int getPrimaryKey(Object o) {
        PhotosBean photo = (PhotosBean) o;
        return photo.getIdPhoto();
    }

    public PhotosBean ResultToBean(ResultSet res) throws SQLException {
        return new PhotosBean(
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

    HashMap<String, Object> BeanToData(Object o) {
        PhotosBean photo = (PhotosBean) o;
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
        return data;
    }
}
