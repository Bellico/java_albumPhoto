package model;

import java.util.HashMap;

public class ResultForm {

    private HashMap<String, String> fields_type = new HashMap<String, String>();
    private HashMap<String, String> fields_value = new HashMap<String, String>();
    private HashMap<String, String> fields_message = new HashMap<String, String>();
    private String[] resultat = new String[2];

    public void clean() {
        fields_type = new HashMap<String, String>();
        fields_value = new HashMap<String, String>();
        fields_message = new HashMap<String, String>();
    }

    public void setResult(String type, String message) {
        resultat[0] = type;
        resultat[1] = message;
    }

    public void setField(String field, String type, String value, String message) {
        fields_type.put(field, type);
        fields_value.put(field, value);
        fields_message.put(field, message);
    }

    public void setField(String field, String type, String value) {
        fields_type.put(field, type);
        fields_value.put(field, value);
        fields_message.put(field, null);
    }

    public String getResultType() {
        return resultat[0];
    }

    public String getResultMessage() {
        return resultat[1];
    }

    public String getType(String field) {
        return fields_type.get(field);
    }

    public String getValue(String field) {
        return fields_value.get(field);
    }

    public String getMessage(String field) {
        return fields_message.get(field);
    }
}
