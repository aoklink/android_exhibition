package com.link.exhibition.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created on 2019/9/16  11:26
 * chenpan pan.chen@linkfeeling.cn
 */
public final class DateUtils {

    public static String getDateForEnglish(long time) {
        //注意传入的要是一个毫秒
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MMMM d",Locale.ENGLISH);
        return sf.format(d);
    }


//    //
//    public static void main(String[] args) {
//        System.out.println(getDateForEnglish(System.currentTimeMillis()));
//    }

}
