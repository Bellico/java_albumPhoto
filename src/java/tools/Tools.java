package tools;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        if (kw.length() > 1) {
            return kw.substring(0, kw.length() - 1);
        } else {
            return "";
        }
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

    public static String FirtLetterToUpper(String s) {
        return s.replaceFirst(".", (s.charAt(0) + "").toUpperCase());
    }

    public static HashMap parseUrl(String url) {
        Pattern p = Pattern.compile("./(.*)");
        Matcher m = p.matcher(url);
        if ((m.find() && m.groupCount() == 1)) {
            String[] tab = m.group(1).split("/");
            HashMap<Integer, String> ht = new HashMap<Integer, String>();
            for (int i = 0; i < tab.length; i++) {
                ht.put(i, tab[i]);
            }
            return ht;
        } else {
            return null;
        }
    }

    public static int toInteger(Object s) {
        try {
            int i = Integer.parseInt((String) s);
            return i;
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
}
