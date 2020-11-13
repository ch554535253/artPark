package com.artPark.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author lbc on 2020/11/3  14:37.
 */
public final class DateUtils {
    private final static SimpleDateFormat YMDHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date getSysDate(){
        LocalDateTime ldt = LocalDateTime.now();
        Instant i = Instant.now();
        return Date.from(i);
    }

    public static String getStrDate(){
        return YMDHms.format(getSysDate());
    }

    public static String getStrDate(SimpleDateFormat format){
        return format.format(getSysDate());
    }

    public static void main(String[] args) {
        System.out.println(getStrDate());
    }

}
