package com.xzkydz.function.transform;

import android.content.Context;

import com.xzkydz.function.utils.SharedPreferencesUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class InsertMotorToLib {
    /**
     * TXT格式电机库文件，写入数据库，第一次打开软件时调用
     */
    public void getMotorDb(final Context context) {
        SharedPreferencesUtils systemParams = SharedPreferencesUtils.getInstance();
        if (!systemParams.getBoolean("isGetMotorDb")) {
            systemParams.setBoolean("isGetMotorDb", true);
        } else {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open("motorlib1.txt"));
                    BufferedReader bufReader = new BufferedReader(inputReader);
                    String line = "";
                    String Result = "";
                    Long count = null;
                    while ((line = bufReader.readLine()) != null) {
                        List<MotorBean> motorBeans = new ArrayList<>();
                        String[] mlibStr = line.split("\t");
                        if (mlibStr.length > 0) {
                            //名称	型号	级数	功率	电流	效率	空载电流	空载功率	电压
                            MotorBean mlib = new MotorBean();
                            mlib.setDJXL(new String(mlibStr[0].getBytes("UTF-8"), "UTF-8"));//序列/名称
                            mlib.setDJ_LIB_NAME(mlibStr[1]);//型号
                            mlib.setJS(mlibStr[2]);//级数
                            mlib.setEDGL(mlibStr[3]);//额定功率
                            mlib.setEDDL(mlibStr[4]);//额定电流
                            mlib.setEDXL(mlibStr[5]);//额定效率
                            mlib.setKZDL(mlibStr[6]);//空载电流
                            mlib.setKZGL(mlibStr[7]);//空载功率
                            mlib.setEDDY(mlibStr[8]);//额定电压
                            mlib.setEDGLYS("0.84");//额定功率因数
                            mlib.setWGJJDL("0.08");//无功经济当量
                            motorBeans.add(mlib);
                        }
                        insertMotor(motorBeans);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public abstract void insertMotor(List<MotorBean> motorBeans);
}
