package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class ControlForm {

    public static final int ENTIER = 1;
    public static final int EMAIL = 2;
    public static final int NON_VIDE = 3;
    public static final int TEXTE_10 = 10;
    public static final int TEXTE_25 = 25;
    public static final int TEXTE_100 = 100;
    public static final String FIELD_ERROR = "important";
    public static final String FIELD_VALID = "";
    public static final String RES_ERROR = "danger";
    public static final String RES_VALID = "success";
    private HttpServletRequest request;
    private HashMap<String, String> errors = new HashMap<String, String>();
    private ArrayList<String> fieldsForm = new ArrayList<String>();
    private ResultForm result = new ResultForm();
    private int nbError = 0;

    public ControlForm(HttpServletRequest request) {
        this.request = request;

    }

    private boolean controlManager(int typeControl, String param) {
        boolean res = false;
        switch (typeControl) {
            case ENTIER:
                res = int_control(param);
                break;
            case TEXTE_10:
            case TEXTE_25:
            case TEXTE_100:
                res = text_control(param, typeControl);
                break;
            case EMAIL:
                res = mail_control(param);
                break;
            case NON_VIDE:
                res = empty_control(param);
                break;
        }
        return res;
    }

    public String check(String field, int typeControl) {
        String value = request.getParameter(field);
        if (value != null) {
            result.setField(field, FIELD_VALID, value);
            boolean res = controlManager(typeControl, value);
            if (!res) {
                result.setField(field, FIELD_ERROR, value);
                nbError++;
            }
        } else {
            result.setField(field, FIELD_ERROR, value);
            nbError++;
        }
        return value;
    }

    public String check(String field, int typeControl, String mError) {
        String value = request.getParameter(field);
        if (value != null) {
            result.setField(field, FIELD_VALID, value);
            boolean res = controlManager(typeControl, value);
            if (!res) {
                result.setField(field, FIELD_ERROR, value, mError);
                nbError++;
            }
        } else {
            result.setField(field, FIELD_ERROR, value, mError);
            nbError++;
        }
        return value;
    }

    public String check(String field, String regex, String mError) {
        String value = request.getParameter(field);
        if (value != null) {
            result.setField(field, FIELD_VALID, value);
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(value);
            boolean res = m.matches();
            if (!res) {
                result.setField(field, FIELD_ERROR, value, mError);
                nbError++;
            }
        } else {
            result.setField(field, FIELD_ERROR, value, mError);
            nbError++;
        }
        return value;
    }

    public String compare(String field1, String field2, String mError) {
        String v1 = request.getParameter(field1);
        String v2 = request.getParameter(field2);
        if (v1 != null && v2 != null) {
            result.setField(field1, FIELD_VALID, v1);
            boolean res = compare_control(v1, v2);
            if (!res) {
                result.setField(field1, FIELD_ERROR, v1, mError);
                nbError++;
            }
        }
        return v1;
    }

    public int getNbError() {
        return nbError;
    }

    public void close() {
        request.setAttribute("form", result);
    }

    public void clean() {
        result.clean();
    }

    public void setResult(String type, String mess) {
        result.setResult(type, mess);
    }

    private boolean int_control(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean text_control(String value, int size) {
        return (value.length() >= size) ? true : false;
    }

    private boolean empty_control(String value) {
        return (!value.equals("")) ? true : false;
    }

    private boolean mail_control(String value) {
        Pattern p = Pattern.compile("^[a-z]+[a-z0-9._-]*@(hotmail|orange|laposte|yahoo|live|gmail|free).(net|fr|org|com)$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    private boolean compare_control(String v1, String v2) {
        return (v1.equals(v2)) ? true : false;
    }
}
