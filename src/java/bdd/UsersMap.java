package bdd;

import bean.UsersBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UsersMap extends FormatQuery {

    public UsersMap() {
        this.table = "users";
        this.primary_key = "idUser";
    }

    public int getPrimaryKey(Object o) {
        UsersBean user = (UsersBean) o;
        return user.getIdUser();
    }

    public UsersBean ResultToBean(ResultSet res) throws SQLException {
        return new UsersBean(
                res.getInt("idUser"),
                res.getString("name"),
                res.getString("firstName"),
                res.getString("password"),
                res.getDate("date_created"),
                res.getTime("time_created"),
                res.getDate("date_lastUpdate"),
                res.getTime("time_lastUpdate"));
    }

    HashMap<String, Object> BeanToData(Object o) {
        UsersBean user = (UsersBean) o;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", user.getName());
        data.put("firstName", user.getFirstName());
        data.put("password", user.getPassword());
        data.put("date_created", user.getDate_created());
        data.put("time_created", user.getTime_created());
        data.put("date_lastUpdate", user.getDate_lastUpdate());
        data.put("time_lastUpdate", user.getTime_lastUpdate());
        return data;
    }
}
