package top.topsea.games.utils;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.SystemClock;
import android.util.Log;

import java.text.ParseException;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateOperation {

    private static final SimpleDateFormat format = new SimpleDateFormat("mm:ss");

    public static long String2LongDate(String before) throws ParseException {
        Date date;
        date = format.parse(before);
        Log.d("DateOperation***", ""+date.getTime());
        return date.getTime();
    }

    public static String Long2StringDate(long before) throws ParseException {
        Date date = new Date(before);
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }


    public static long String2Long(String strTime) {
        String[] timeArray=strTime.split(":");
        long longTime=0;
        if (timeArray.length==2) {//如果时间是MM:SS格式
            longTime=Integer.parseInt(timeArray[0])*1000*60+Integer.parseInt(timeArray[1])*1000;
        }else if (timeArray.length==3){//如果时间是HH:MM:SS格式
            longTime=Integer.parseInt(timeArray[0])*1000*60*60+Integer.parseInt(timeArray[1])
                    *1000*60+Integer.parseInt(timeArray[0])*1000;
        }
        return SystemClock.elapsedRealtime()-longTime;
    }
}
