package model;

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
    private HttpServletRequest request;
    private HashMap<String, String> form = new HashMap<String, String>();
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
            request.setAttribute(field, value);
            boolean res = controlManager(typeControl, value);
            if (!res) {
                nbError++;
            }
        }
        return value;
    }

    public String check(String field, int typeControl, String mError) {
        String value = request.getParameter(field);
        if (value != null) {
            request.setAttribute(field, value);
            boolean res = controlManager(typeControl, value);
            if (!res) {
                nbError++;
                form.put(field, mError);
            }
        }
        return value;
    }

    public String compare(String field1, String field2, String mError) {
        String v1 = request.getParameter(field1);
        String v2 = request.getParameter(field2);
        if (v1 != null && v2 != null) {
            request.setAttribute(field1, v1);
            request.setAttribute(field2, v2);
            boolean res = compare_control(v1, v2);
            if (!res) {
                nbError++;
                form.put(field2, mError);
            }
        }
        return v1;
    }

    public int getNbError() {
        return nbError;
    }

    public void close() {
        request.setAttribute("form", form);
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
