package tools;

import java.sql.Date;
import java.sql.Time;
import java.util.GregorianCalendar;

public class Tools {

    private static GregorianCalendar calendar = new GregorianCalendar();

    public static Date DateNow() {
        return new java.sql.Date(calendar.getTimeInMillis());
        //String date = calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)
    }

    public static Time TimeNow() {
        return new java.sql.Time(calendar.getTimeInMillis());
    }
    
    public static String generate_KeyWord(String s){
        return null;
    }
    
    public static String crypt(String s){
        //MD5 ou autre
        return null;
    }
}
