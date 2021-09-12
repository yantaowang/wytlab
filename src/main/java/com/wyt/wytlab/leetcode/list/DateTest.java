package com.wyt.wytlab.leetcode.list;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf2.format(new Date()));
        Date end = sdf2.parse("2019-08-05 10:14:44");
        Date start = sdf2.parse("2019-08-05 13:11:44");
        System.out.println(getTimeDifference(start,end));
    }

    /***
     * @comments 计算两个时间的时间差
     */
    public static String getTimeDifference(Date start,Date end) {
        try{
            long l=end.getTime()-start.getTime();       //获取时间差
            long day= l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            long min=((l/(60*1000))-day*24*60-hour*60);
            long s=(l/1000-day*24*60*60-hour*60*60-min*60);
            return String.format("%02d:%02d:%02d", Math.abs(hour),Math.abs(min),Math.abs(s));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
