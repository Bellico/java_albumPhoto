package bdd;

import bean.UserBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserMap extends SQLMapping {

    public UserMap() {
        this.table = "users";
        this.primary_key = "idUser";
    }

    @Override
    protected int getPrimaryKey(Object o) {
        UserBean user = (UserBean) o;
        return user.getIdUser();
    }

    @Override
    public UserBean ResultToBean(ResultSet res) throws SQLException {
        return new UserBean(
                res.getInt("idUser"),
                res.getString("name"),
                res.getString("firstName"),
                res.getString("password"),
                res.getDate("date_created"),
                res.getTime("time_created"),
                res.getDate("date_lastUpdate"),
                res.getTime("time_lastUpdate"));
    }

    @Override
    public HashMap<String, Object> BeanToData(Object o) {
        UserBean user = (UserBean) o;
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
