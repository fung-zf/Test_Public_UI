package com.xzkydz.function.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    public static int GREATED_TASK = 0;
    public static int TIME_NORMAL_1 = 1;
    public static int TIME_NORMAL_2 = 2;

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTime(int type) {
        Date date = new Date();// 创建一个时间对象，获取到当前的时间
        SimpleDateFormat
                sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),// 设置时间显示格式
                sdf1 = new SimpleDateFormat("yyyyMMddHHmmss"),
                sdf2 = new SimpleDateFormat("yyyyMMdd"),
                sdf3 = new SimpleDateFormat("yyyy-MM-dd");

        String res = "";

        switch (type) {
            case 0:
                res =  sdf0.format(date);
            break;
            case 1:
                res =  sdf1.format(date);
            break;
            case 2:
                res =  sdf2.format(date);
            break;
            case 3:
                res =  sdf3.format(date);
            break;
        }
        return res;
    }


    /**
     * 创建测试任务的时间格式
     * @return
     */
    public static String getGreatedTaskTime(){
        String res = "";
        Date date = new Date();// 创建一个时间对象，获取到当前的时间
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
        return sdf0.format(date);
    }

}
