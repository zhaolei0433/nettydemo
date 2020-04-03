package test1;

import com.ipanel.web.base.BaseUtil.gson.GsonUtil;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author zhaolei
 * Create: 2019/1/4 17:57
 * Modified By:
 * Description:
 */
public class DataTest {

    public static final String getUrlPathFileName(String url){
        int i = url.lastIndexOf("/");
        return  url.substring(i+1);
    }

    public static String format(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
    }

    public static String getNowTime(){
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(Instant.now().toEpochMilli());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(c.getTime());
    }

    public static String getBeforeTime(long time){
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(Instant.now().toEpochMilli() - time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(c.getTime());
    }

    public static String getNowTime2(){
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(Instant.now().toEpochMilli());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime()) + " 00:00:00";
    }

    public static void main(String[] args) {
       /* System.out.println("instant使用————————————");
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(new Date().getTime());
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println("**********************");*/


        System.out.println(getNowTime());
        System.out.println(getBeforeTime(600000));
        System.out.println(getNowTime2());

        /*LocalDateTime date = LocalDateTime.now();
        System.out.println("当前日期=" + date);*/

       /* String urlArray = "[\"http://city.eastday.com/images/thumbnailimg/month_1905/5bae508e9a6d4698adec6ef64c98d502.jpg\"]";
        List<String> ulrs = GsonUtil.jsonToList(urlArray,String.class);
        System.out.println(ulrs.toString());*/


        /* System.out.println(new ArrayList<Integer>());*/
       /* String a = "172.168.20.5";
        String b = "172.";
        System.out.println(a.compareTo(b));*/
/*
        Calendar c=Calendar.getInstance();
        int seconds = 1550222399;//数据库中提取的数据
        long millions=new Long(seconds).longValue()*1000;
        c.setTimeInMillis(millions);
        System.out.println(""+c.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(c.getTime());
        System.out.println(dateString);*/

    }

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    /*public static String convertTimeToString(Long time) {
       *//* //Assert.notNull(time, "time is null");
        DateFormatter dateFormatter = DateFormatter.ofPattern("yyyy-MM-dd");
        return dateFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));*//*
    }

    /**
     * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
     */
    public static Long convertTimeToLong(String time) {
        //Assert.notNull(time, "time is null");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(time, dateTimeFormatter);
        return LocalDateTime.from(localDateTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
    /**
     * 其他时间工具
     */
    /**
     * 取本月第一天
     */
    public static LocalDate firstDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 取本月第N天
     */
    public static LocalDate dayOfThisMonth(int n) {
        LocalDate today = LocalDate.now();
        return today.withDayOfMonth(n);
    }

    /**
     * 取本月最后一天
     */
    public static LocalDate lastDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 取本月第一天的开始时间
     */
    public static LocalDateTime startOfThisMonth() {
        return LocalDateTime.of(firstDayOfThisMonth(), LocalTime.MIN);
    }


    /**
     * 取本月最后一天的结束时间
     */
    public static LocalDateTime endOfThisMonth() {
        return LocalDateTime.of(lastDayOfThisMonth(), LocalTime.MAX);
    }
}
