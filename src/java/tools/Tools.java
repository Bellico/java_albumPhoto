package tools;

import java.sql.Date;
import java.sql.Time;
import java.util.GregorianCalendar;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class Tools {

    private static GregorianCalendar calendar = new GregorianCalendar();

    public static Date DateNow() {
        return new java.sql.Date(calendar.getTimeInMillis());
        //String date = calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)
    }

    public static Time TimeNow() {
        return new java.sql.Time(calendar.getTimeInMillis());
    }

    public static String generate_KeyWord(String s) {
        String[] carac = new String[]{"'", "?", "!", ".", ":", ";", "-", ","};
        for (String i : carac) {
            s = s.replace(i, "");
        }
        String kw = "";
        String[] d = s.split(" ");
        for (int i = 0; i < d.length; i++) {
            if (d[i].length() > 4) {
                kw += d[i] + "-";
            }
        }
        return kw.substring(0, kw.length() - 1);
    }

    public static String crypt(String s) {
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("MD5"); //SHA-256
        passwordEncryptor.setPlainDigest(true);
        return passwordEncryptor.encryptPassword(s);
    }
}
