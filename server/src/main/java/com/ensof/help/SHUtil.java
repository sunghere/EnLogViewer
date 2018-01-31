package com.ensof.help;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SungHere on 2018-01-15.
 */
public class SHUtil {


    public static String getTimePatternYYYYMMDDHHmmssSSS(String time) {

        if (time != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date trDate = null;
            try {
                trDate = simpleDateFormat.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSS");
            time = simpleDateFormat.format(trDate);
        } else {
            time = "";
        }

        return time;
    }



}
