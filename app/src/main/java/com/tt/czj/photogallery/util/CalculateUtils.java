package com.tt.czj.photogallery.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by czj on 15-10-22.
 */
public class CalculateUtils {

    private static SimpleDateFormat mSimpleDateFormat_Minutes = new SimpleDateFormat("mm:ss");
    private static SimpleDateFormat mSimpleDateFormat_Hours = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat mSimpleDateFormat_Date = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Gets string duration time from long.
     *
     * @param duration the duration
     * @return the string duration time from long
     */
//input time unit is micro second.
    public static String getStringDurationTimeFromLong(long duration) {
        if(duration < 1000){
            duration = 1000;
        }
        return mSimpleDateFormat_Minutes.format(new Date(duration)); //millisecond value
    }

    /**
     * Get date time string from seconds string.
     *
     * @param time the time
     * @return the string
     */
//input time unit is second.
    public static String getDateTimeStringFromSeconds(long time){
        return mSimpleDateFormat_Date.format(new Date(time*1000L));
    }

}
