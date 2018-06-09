package com.bignerdranch.android.criminalintent.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String date2String(Date date){
        //创建日期格式化对象   因为DateFormat类为抽象类 所以不能new
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");
        String format = bf.format(date);//格式化 bf.format(date);
        return format;
    }

    public static Date string2Date(String s){
        //创建日期格式化对象   因为DateFormat类为抽象类 所以不能new
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");
        Date parse = null;
        try {
            parse = bf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
