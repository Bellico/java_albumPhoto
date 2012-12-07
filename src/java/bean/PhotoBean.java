package bean;

import java.sql.Date;
import java.sql.Time;
import tools.Tools;

public class PhotoBean {

    private int idPhoto;
    private String title;
    private String descr;
    private int width;
    private int height;
    private String img;
    private String keyword;
    private Date date_created;
    private Time time_created;
    private Date date_lastUpdate;
    private Time time_lastUpdate;
    private int idAlbum;

    public PhotoBean() {
        this.idPhoto = -1;
        this.title = "";
        this.descr = "";
        this.width = 0;
        this.height = 0;
        this.img = "";
        this.keyword = "";
        this.date_created = Tools.DateNow();
        this.time_created = Tools.TimeNow();
        this.date_lastUpdate = Tools.DateNow();
        this.time_lastUpdate = Tools.TimeNow();
    }

    public PhotoBean(int idPhoto, String title, String descr, int width, int height, String img, String keyword, Date date_created, Time time_created, Date date_lastUpdate, Time time_lastUpdate, int idAlbum) {
        this.idPhoto = idPhoto;
        this.title = title;
        this.descr = descr;
        this.width = width;
        this.height = height;
        this.img = img;
        this.keyword = keyword;
        this.date_created = date_created;
        this.time_created = time_created;
        this.date_lastUpdate = date_lastUpdate;
        this.time_lastUpdate = time_lastUpdate;
        this.idAlbum = idAlbum;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Tools.FirtLetterToUpper(title);
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = Tools.FirtLetterToUpper(descr);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getDate_created() {
        return date_created;
    }

    public Time getTime_created() {
        return time_created;
    }

    public Date getDate_lastUpdate() {
        return date_lastUpdate;
    }

    public void setDate_lastUpdate() {
        this.date_lastUpdate = Tools.DateNow();
    }

    public Time getTime_lastUpdate() {
        return time_lastUpdate;
    }

    public void setTime_lastUpdate() {
        this.time_lastUpdate = Tools.TimeNow();
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getDateCreated() {
        return Tools.DateToString(getDate_created(), getTime_created());
    }

    public String getDateLastUpdate() {
        return Tools.DateToString(getDate_lastUpdate(), getTime_lastUpdate());
    }
}
