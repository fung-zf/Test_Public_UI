package com.xzkydz.function.gaspublicfunction;

import android.util.Log;

import com.xzkydz.function.motor.module.IMotorBean;
import com.xzkydz.function.motor.view.MotorSelectorActivity;
import com.xzkydz.greendao.bean.MotorEnity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MySearchMorotActivtiy  extends MotorSelectorActivity{

    private List<IMotorBean> motorBeans;

    // 获取电机库
    @Override
    public List<IMotorBean> getMotorList() {
        motorBeans = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MotorEnity> motorBeanList = MyApp.getInstance().getDaoInstant().getMotorEnityDao().loadAll();
                Collections.reverse(motorBeanList);
                for (MotorEnity mo : motorBeanList) {
                    MotorBean motorBean = new MotorBean();
                    motorBean.setId(mo.getId());
                    motorBean.setAdd(mo.getIsAdd());
                    motorBean.setDjxlStr(mo.getDJXL());
                    motorBean.setDjxhStr(mo.getDJ_LIB_NAME());
                    motorBean.setEddyStr(mo.getEDDY());
                    motorBean.setEddlStr(mo.getEddl());
                    motorBean.setEdglStr(mo.getEdgl());
                    motorBean.setEdxlStr(mo.getEdxl());
                    motorBean.setKzdlStr(mo.getKzdl());
                    motorBean.setKzglStr(mo.getKzgl());
                    motorBean.setEdglysStr(mo.getEdgl());
                    motorBean.setJsStr(mo.getJS());
                    motorBean.setWgjjdlStr(mo.getWgjjdl());
                    motorBeans.add(motorBean);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        initListView(motorBeans);
                    }
                });
            }
        }).start();
        return motorBeans;
    }

    // 插入电机库
    @Override
    public void insertNewMotor(final com.xzkydz.function.transform.MotorBean motorBean) {
        Log.d("asd", "insertNewMotor: " + motorBean.getDJ_LIB_NAME());
        MotorBean motorBean1 = new MotorBean();
        motorBean1.setDjxhStr(motorBean.getDJ_LIB_NAME());
        motorBean1.setEddyStr(motorBean.getEDDY());
        motorBean1.setEddlStr(motorBean.getEDDL());
        motorBean1.setEdglStr(motorBean.getEDGL());
        motorBean1.setEdxlStr(motorBean.getEDXL());
        motorBean1.setJsStr(motorBean.getJS());
        motorBean1.setKzdlStr(motorBean.getKZDL());
        motorBean1.setKzglStr(motorBean.getKZGL());
        motorBean1.setWgjjdlStr(motorBean.getWGJJDL());
        motorBean1.setAdd(true);
        motorBean1.setDjxlStr("新创建");
        motorBeans.add(0,motorBean1);
        setMotorAllList(motorBeans);
        initListView(motorBeans);


        new Thread(new Runnable() {
            @Override
            public void run() {
                MotorEnity motorEnity = new MotorEnity();
                motorEnity.setIsAdd(true);
                motorEnity.setDJ_LIB_NAME(motorBean.getDJ_LIB_NAME());
                motorEnity.setEDDY(motorBean.getEDDY());
                motorEnity.setEDDL(motorBean.getEDDL());
                motorEnity.setEDGL(motorBean.getEDGL());
                motorEnity.setEDXL(motorBean.getEDXL());
                motorEnity.setJS(motorBean.getJS());
                motorEnity.setKZDL(motorBean.getKZDL());
                motorEnity.setKZGL(motorBean.getKZGL());
                motorEnity.setWGJJDL(motorBean.getWGJJDL());
                motorEnity.setDJXL("录入");
                MyApp.getInstance().getDaoInstant().getMotorEnityDao().insert(motorEnity);
            }
        }).start();
    }

    // 删除电机库
    @Override
    public void deleteMotor(IMotorBean motorBean) {
        motorBeans.remove(motorBean);

        setMotorAllList(motorBeans);
        initListView(motorBeans);
    }
}
