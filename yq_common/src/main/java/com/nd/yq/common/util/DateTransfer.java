package com.nd.yq.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author NirVana
 * @version 1.0
 * @package HBaseUtil
 * @create 2022-07-18 9:32
 * @discrpit TODO
 */
public class DateTransfer {
    public static boolean isLastDay(String date){
        String month = date.substring(4,6);
        String day = date.substring(6,8);
        if(month.equals("01") ||month.equals("03") ||month.equals("05") ||month.equals("07")
                ||month.equals("08") ||month.equals("10") ||month.equals("12"))
            return day.equals("31");
        if(month.equals("04") ||month.equals("06") ||month.equals("09") ||month.equals("11"))
            return day.equals("30");
        return day.equals("29");
    }
    public static String getNextDay(String date) {
        DateFormat dft = new SimpleDateFormat("yyyyMMdd");
        String afterDay = null;
        try {
            Date temp = dft.parse(date);
            Calendar cld = Calendar.getInstance();
            cld.setTime(temp);
            cld.add(Calendar.DATE, 1);
            temp = cld.getTime();
            afterDay = dft.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return afterDay;
    }
    public static String getNextMonth(String date) {
        DateFormat dft = new SimpleDateFormat("yyyyMMdd");
        String afterDay = null;
        try {
            Date temp = dft.parse(date);
            Calendar cld = Calendar.getInstance();
            cld.setTime(temp);
            cld.add(Calendar.MONTH, 1);
            temp = cld.getTime();
            afterDay = dft.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return afterDay;
    }
    
    public static String dateTrans (String date, String beforeFormat, String afterFormat){
        DateFormat dft = new SimpleDateFormat(beforeFormat);
        DateFormat afterDft = new SimpleDateFormat(afterFormat);
        String afterDay = null;
        try {
            Date temp = dft.parse(date);
            afterDay = afterDft.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return afterDay;    
    }
}
