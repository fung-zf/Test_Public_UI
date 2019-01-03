package com.xzkydz.function.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.xzkydz.function.data.all.view.AllTaskFragment;
import com.xzkydz.function.data.uncomplete.view.UnCompleteFragment;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;
import com.xzkydz.function.utils.SharedPreferencesUtils;

/**
 * Date: 2018/3/19  9:54
 * Description:
 */

public class KyApp extends Application {

    public static  AllTaskFragment allTaskFragment;
    public static  UnCompleteFragment unCompleteFragment;

    public static String urlStr;
    public static String portStr;
    public static String appKeyStr;
    public static String numberStr = 1+"";
    public static String zjrStr = 12000+"";
    public static KyApp myApp;
    @Override
    public void onCreate() {
        setAppStyleColor();
        super.onCreate();
        SharedPreferencesUtils.init(this);
        myApp = this;
        getPara();
    }
    /**
     * 设置整个APP的风格
     */
    public void setAppStyleColor(){
        AppStyle.appToolbarColor = R.color.lib_tittleBar;
    }
    //

    /**
     * 获取最近一次使用的参数
     */
    public void getPara(){
        SharedPreferences sharedPreferences = getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        urlStr = "http://" + sharedPreferences.getString("ip","60.168.132.40") + ":" + sharedPreferences.getString("port","8085") + "/lims-platform/api/operate.shtml";
        portStr = sharedPreferences.getString("port","8085");
        appKeyStr = sharedPreferences.getString("appKeyStr", "xzky");
    }

}
