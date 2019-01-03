package com.xzkydz.function.gaspublicfunction;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.greendao.manager.DaoMaster;
import com.greendao.manager.DaoSession;
import com.xzkydz.function.app.KyApp;
import com.xzkydz.function.style.AppStyle;
import com.xzkydz.function.utils.DateUtil;
import com.xzkydz.function.utils.SharedPreferencesUtils;
import com.xzkydz.greendao.bean.TaskEnity;
import com.xzkydz.greendao.bean.TaskResEnity;

/**
 * Date: 2018/3/19  10:54
 * Description: 在此处设置APP的整体风格
 */

public class MyApp extends KyApp {

    private static DaoSession daoSession;
    private static TaskEnity taskEnity = new TaskEnity(); //测试任务
    private static TaskResEnity taskResEnity = new TaskResEnity(); //测试数据
    public static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
        initAllData();
        insertTxtMotor();
        myApp = MyApp.this;
    }


    @Override
    public void setAppStyleColor() {
        super.setAppStyleColor();
        AppStyle.appNameId = R.string.app_name;
        AppStyle.appToolbarColor = R.color.lib_tittleBar;
        SharedPreferencesUtils.init(this);
    }


    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //gasepump.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(), "database.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    /*
     * description：将Txt格式的电机数据库转换为sqlite
     */
    private void insertTxtMotor(){
        SharedPreferences sp = getSharedPreferences("GreatedMotorDb", Context.MODE_PRIVATE);
        boolean greatedBl =  sp.getBoolean("insert",false);

        if (!greatedBl){
            MotorTxtToDb motorTxtToDb = new MotorTxtToDb();
            motorTxtToDb.getMotorDb(getApplicationContext());
        }
        sp.edit().putBoolean("insert", true).commit();
    }

    /**
     *  Description: 初始化全局变量，确保在对一次任务进行测试、增删改查的时候，所用的变量只需一个地方获取，全局通用。
     */
    private void initAllData(){
        taskEnity = null; // 正在操作的测试任务

    }


    public static TaskResEnity getTaskResEnity() {
        return taskResEnity;
    }

    public static void setTaskResEnity(TaskResEnity taskResEnity) {
        MyApp.taskResEnity = taskResEnity;
    }

    public static TaskEnity getTaskEnity() {
        return taskEnity;
    }


    public static void setTaskEnity(TaskEnity taskEnity) {
        MyApp.taskEnity = taskEnity;
    }

    public static MyApp getInstance() {
        return myApp;
    }


}
