package com.zz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * util date转Sql date
 * @param
 *@return
 */
public class DateUtil {
    public static java.sql.Date tranceToSqlDate(Date udate){
        //转成sqldate
        return new java.sql.Date(udate.getTime());
    }


/**
 * string 转utildate
 * @param str
 * @return
 */
public static Date tranceToDate(String str) {
    SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d1=null;
    try {
         d1=s.parse(str);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return d1;
}

    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

}
