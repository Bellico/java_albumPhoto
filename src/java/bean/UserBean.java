package bean;

import java.sql.Date;
import java.sql.Time;
import tools.Tools;

public class UserBean {

    private int idUser;
    private String name;
    private String firstName;
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

    public UserBean(int idUser, String name, String firstName, String password, Date date_created, Time time_created, Date date_lastUpdate, Time time_lastUpdate) {
        this.idUser = idUser;
        this.name = name;
        this.firstName = firstName;
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
}
