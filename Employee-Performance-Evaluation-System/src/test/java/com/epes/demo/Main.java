package com.epes.demo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
* @author 程龙
* @version 创建时间：2019年10月27日 上午10:15:03
* @ClassName 类名称：
* @Description 类描述：
*/
public class Main {
    public static void main(String[] args) {
       // System.out.println(Calendar.YEAR);
        //System.out.println(Calendar.MONTH);
//        Calendar calendar = Calendar.getInstance();
//        int y = calendar.getTime().getYear();
//        System.out.println(y);
//        System.out.println(Calendar.getInstance().getTime());
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        System.out.println(c.get(Calendar.YEAR));
        System.out.println(c.get(Calendar.MONTH));
        int time = c.get(Calendar.HOUR_OF_DAY);
        //获取当前分钟
        int min = c.get(Calendar.MINUTE);
        //获取当前秒
        int sec = c.get(Calendar.SECOND); 
        System.out.println(time+" "+min+" "+sec);
        System.out.println(Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")).get(Calendar.YEAR)+"-"+(Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")).get(Calendar.MONTH)+1));
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("a", "1");
        map.put("b", "2");
        System.out.println(map.toString());
    }
}
