package tools;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

public class Tools {

    public static String appPath = "";
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";

    public static Date DateNow() {
        GregorianCalendar calendar = new GregorianCalendar();
        return new java.sql.Date(calendar.getTimeInMillis());
        //String date = calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)
    }

    public static Time TimeNow() {
        GregorianCalendar calendar = new GregorianCalendar();
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

    public static String crypt(String s, String algo, boolean salage) {
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        passwordEncryptor.setAlgorithm(algo);
        passwordEncryptor.setPlainDigest(salage);
        return passwordEncryptor.encryptPassword(s);
    }

    public static boolean in_Array(String r, String[] tab) {
        int i = 0;
        boolean find = false;
        while (i < tab.length && !find) {
            if (tab[i].equals(r)) {
                find = true;
            }
            i++;
        }
        return find;
    }

    public static String DateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        return (date != null) ? "le " + format.format(date) : "";
    }

    public static String TimeToString(Time time) {
        SimpleDateFormat format = new SimpleDateFormat(" à HH:mm");
        return (time != null) ? format.format(time) : "";
    }

    public static String DateToString(Date date, Time time) {
        return DateToString(date) + TimeToString(time);
    }
}
