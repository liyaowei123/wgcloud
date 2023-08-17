package edu.jit.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//时间工具类，一般工具类中放置的都是公共静态方法，方便调用
public class DateUtil {
    public static String dateFormat(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    public static String timeFormat(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    public static ArrayList<String> getLastWeek(){
        Date now=new Date();
        long timestamp = now.getTime();//当前日期的时间戳
        ArrayList<String> lastWeekList=new ArrayList<>();
        lastWeekList.add(dateFormat(now));
        for (int i = 0; i < 7; i++) {
            timestamp-=1000*3600*24;
            Date day=new Date(timestamp);//前一天的时间对象
            lastWeekList.add(dateFormat(day));
        }
        return lastWeekList;
    }
}
