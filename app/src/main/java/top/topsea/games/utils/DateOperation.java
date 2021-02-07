package top.topsea.games.utils;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.text.ParseException;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateOperation {

    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

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
}
