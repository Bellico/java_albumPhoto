package bdd;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class SQLMapping<T> extends Database {

    protected String table = "table";
    protected String primary_key = "id";
    protected boolean queryPrepare = true;
    public static final String ORDER_BY_DATE = "date_lastUpdate DESC, time_lastUpdate DESC";

    abstract int getPrimaryKey(Object o);

    abstract Object ResultToBean(ResultSet r) throws SQLException;

    abstract HashMap<String, Object> BeanToData(Object o);

    public void setQueryPrepare(boolean queryPrepare) {
        this.queryPrepare = queryPrepare;
    }

    public ArrayList<Object> getAll(String order) {
        String orderBy = (order != null) ? order : primary_key + " DESC ";
        ArrayList<Object> list = new ArrayList<Object>();
        ResultSet res;
        try {
            if (queryPrepare) {
                String nameStatement = "getall-" + table;
                PreparedStatement statement = prepareQuery(nameStatement, "select * from " + table + " ORDER BY " + orderBy);
                res = statement.executeQuery();
            } else {
                res = query("select * from " + table + " ORDER BY " + orderBy);
            }
            while (res.next()) {
                list.add(ResultToBean(res));
            }
        } catch (SQLException ex) {
            System.out.println("[ Erreur " + table + ">>getAll ] : " + ex.getMessage());
        }
        return list;
    }

    private ResultSet findByAttr(String attr, Object value, String order) throws SQLException {
        String orderBy = (order != null) ? order : primary_key + " DESC ";
        ResultSet res;
        if (queryPrepare) {
            String nameStatement = "findByAttr-" + table + "-" + attr;
            PreparedStatement statement = prepareQuery(nameStatement, "select * from " + table + " where " + attr + "= ? ORDER BY " + orderBy);
            setStatement(statement, value, 1);
            res = statement.executeQuery();
        } else {
            res = query("select * from " + table + " where " + attr + "=" + value + "ORDER BY " + orderBy);
        }
        return res;
    }

    public Object getbyId(int id) {
        try {
            ResultSet res = findByAttr(primary_key, id, null);
            res.next();
            return ResultToBean(res);
        } catch (SQLException ex) {
            System.out.println("[ Erreur " + table + ">>getbyId ] : " + ex.getMessage());
            return null;
        }
    }

    public Object getbyId(int id, String orderBy) {
        try {
            ResultSet res = findByAttr(primary_key, id, orderBy);
            res.next();
            return ResultToBean(res);
        } catch (SQLException ex) {
            System.out.println("[ Erreur " + table + ">>getbyId ] : " + ex.getMessage());
            return null;
        }
    }

    public Object getbyAttr(String attr, Object value) {
        try {
            ResultSet res = findByAttr(attr, value, null);
            res.next();
            return ResultToBean(res);
        } catch (SQLException ex) {
            System.out.println("[ Erreur " + table + ">>getbyAttr ] : " + ex.getMessage());
            return null;
        }
    }

    public Object getbyAttr(String attr, Object value, String orderBy) {
        try {
            ResultSet res = findByAttr(attr, value, orderBy);
            res.next();
            return ResultToBean(res);
        } catch (SQLException ex) {
            System.out.println("[ Erreur " + table + ">>getbyAttr ] : " + ex.getMessage());
            return null;
        }
    }

    public ArrayList<Object> getAllbyAttr(String attr, Object value) {
        ArrayList<Object> list = new ArrayList<Object>();
        try {
            ResultSet res = findByAttr(attr, value, null);
            while (res.next()) {
                list.add(ResultToBean(res));
            }
        } catch (SQLException ex) {
            System.out.println("[ Erreur " + table + ">>getAllbyAttr ] : " + ex.getMessage());

        }
        return list;
    }

    public ArrayList<Object> getAllbyAttr(String attr, Object value, String orderBy) {
        ArrayList<Object> list = new ArrayList<Object>();
        try {
            ResultSet res = findByAttr(attr, value, orderBy);
            while (res.next()) {
                list.add(ResultToBean(res));
            }
        } catch (SQLException ex) {
            System.out.println("[ Erreur " + table + ">>getAllbyAttr ] : " + ex.getMessage());

        }
        return list;
    }

    private int insert(HashMap<String, Object> data) throws SQLException {
        String sql = "insert into " + table;
        String fields = "(";
        String values = "(";
        for (String mapKey : data.keySet()) {
            fields += mapKey + ",";
            if (queryPrepare) {
                values += "?,";
            } else {
                if (data.get(mapKey) != null || !(data.get(mapKey) instanceof Integer)) {
                    values += "'" + data.get(mapKey) + "',";
                } else {
                    values += data.get(mapKey) + ",";
                }
            }
        }
        fields = fields.substring(0, fields.length() - 1);
        fields += ")";
        values = values.substring(0, values.length() - 1);
        values += ")";
        sql += fields + " values " + values;

        if (queryPrepare) {
            String nameStatement = "insert-" + table;
            PreparedStatement statement = prepareQuery(nameStatement, sql);
            int i = 1;
            for (String mapKey : data.keySet()) {
                setStatement(statement, data.get(mapKey), i);
                i++;
            }
            return statement.executeUpdate();
        } else {
            return update(sql);
        }
    }

    private int update(HashMap<String, Object> data, String attr, int value) throws SQLException {
        String sql = "update " + table + " SET ";
        for (String mapKey : data.keySet()) {
            if (queryPrepare) {
                sql += mapKey + "=?,";
            } else {
                if (data.get(mapKey) != null || !(data.get(mapKey) instanceof Integer)) {
                    sql += mapKey + "='" + data.get(mapKey) + "',";
                } else {
                    sql += mapKey + "=" + data.get(mapKey) + ",";
                }
            }
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += " WHERE " + attr;
        sql += (queryPrepare) ? "=?" : "=" + value;

        if (queryPrepare) {
            String nameStatement = "update-" + table + "-" + attr;
            PreparedStatement statement = prepareQuery(nameStatement, sql);
            int i = 1;
            for (String mapKey : data.keySet()) {
                setStatement(statement, data.get(mapKey), i);
                i++;
            }
            setStatement(statement, value, i);
            return statement.executeUpdate();
        } else {
            return update(sql);
        }
    }

    private int delete(String attr, int value) throws SQLException {
        if (queryPrepare) {
            String nameStatement = "delete-" + table + "-" + attr;
            PreparedStatement statement = prepareQuery(nameStatement, "delete from " + table + " where " + attr + "= ?");
            setStatement(statement, value, 1);
            return statement.executeUpdate();
        } else {
            return update("delete from " + table + " where " + attr + "=" + value);
        }
    }

    protected void setStatement(PreparedStatement s, Object value, int pos) throws SQLException {
        if (value == null) {
            s.setString(pos, null);
        } else if (value instanceof String) {
            s.setString(pos, (String) value);
        } else if (value instanceof Integer) {
            s.setInt(pos, (Integer) value);
        } else if (value instanceof Date) {
            s.setDate(pos, (Date) value);
        } else if (value instanceof Time) {
            s.setTime(pos, (Time) value);
        } else if (value instanceof Boolean) {
            s.setBoolean(pos, (Boolean) value);
        }
    }

    public int save(Object o) {
        HashMap<String, Object> data = BeanToData(o);
        int res = 0;
        if (o != null) {
            int id = getPrimaryKey(o);
            try {
                if (id > 0) {
                    res = update(data, "idUser", id);
                } else {
                    res = insert(data);
                }
            } catch (SQLException ex) {
                System.out.println("[ Erreur Methode Save ] : " + ex.getMessage());
                res = 0;
            }
        }
        return res;
    }

    public int delete(Object o) {
        int res = 0;
        if (o != null) {
            try {
                res = delete(primary_key, getPrimaryKey(o));
            } catch (SQLException ex) {
                System.out.println("[ Erreur Methode Delete ] : " + ex.getMessage());
                res = 0;
            }
        }
        return res;
    }
}