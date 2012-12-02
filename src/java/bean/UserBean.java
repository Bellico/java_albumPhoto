package bean;

import bdd.AlbumMap;
import bdd.PhotoMap;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import tools.Tools;

public class UserBean {

    private int idUser;
    private String name;
    private String firstName;
    private String login;
    private String password;
    private Date date_created;
    private Time time_created;
    private Date date_lastUpdate;
    private Time time_lastUpdate;

    public UserBean() {
        this.idUser = -1;
        this.name = "";
        this.firstName = "";
        this.password = "";
        this.date_created = Tools.DateNow();
        this.time_created = Tools.TimeNow();
        this.date_lastUpdate = Tools.DateNow();
        this.time_lastUpdate = Tools.TimeNow();
    }

    public UserBean(int idUser, String name, String firstName, String login, String password, Date date_created, Time time_created, Date date_lastUpdate, Time time_lastUpdate) {
        this.idUser = idUser;
        this.name = name;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.date_created = date_created;
        this.time_created = time_created;
        this.date_lastUpdate = date_lastUpdate;
        this.time_lastUpdate = time_lastUpdate;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getNbAlbum() {
        AlbumMap mapAlbum = new AlbumMap();
        ArrayList<AlbumBean> albums = mapAlbum.getAllbyAttr("idUser ", idUser);
        return albums.size();
    }

    public int getNbPhoto() {
        AlbumMap mapAlbum = new AlbumMap();
        PhotoMap mapPhoto = new PhotoMap();
        ArrayList<AlbumBean> albums = mapAlbum.getAllbyAttr("idUser ", idUser);
        int nbPhotos = 0;
        for (AlbumBean al : albums) {
            ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", al.getIdAlbum());
            nbPhotos += photos.size();
        }
        return nbPhotos;
    }

    public String getDateCreated() {
        return Tools.DateToString(getDate_created(), getTime_created());
    }

    public String getDateLastUpdate() {
        return Tools.DateToString(getDate_lastUpdate(), getTime_lastUpdate());
    }
}
