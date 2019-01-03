package com.xzkydz.function.motor.module;

import android.util.Log;

import com.xzkydz.function.search.view.CharacterParser;

import java.util.ArrayList;
import java.util.List;

public class MotorModule implements IMotorModule {

    private List<IMotorBean> djxhQueryResList;
    private List<IMotorBean> edglQueryResList;
    private List<IMotorBean> eddyQueryResList;
    private List<IMotorBean> resQueryList;
    private List<IMotorBean> allIMotorBean;
    private String djxhStr;
    private String edglStr;
    private String eddyStr;
    private CharacterParser characterParser = CharacterParser.getInstance();  //实例化汉字转拼音类

    public MotorModule() {
    }

    public MotorModule(List<IMotorBean> iMotorBeans ,String djxhStr, String edglStr, String eddyStr) {
        this.djxhStr = djxhStr;
        this.edglStr = edglStr;
        this.eddyStr = eddyStr;
        this.allIMotorBean = iMotorBeans;
    }

    public MotorModule setAllMotorBean(List<IMotorBean> motorModules) {
        this.allIMotorBean = motorModules;
        return this;
    }

    public MotorModule setDjxhStr(String djxhStr) {
        this.djxhStr = djxhStr;
        return this;
    }

    public MotorModule setEdglStr(String edglStr) {
        this.edglStr = edglStr;
        return this;
    }

    public MotorModule setEddyStr(String eddyStr) {
        this.eddyStr = eddyStr;
        return this;
    }

    public void getResList(){
        djxhQueryResList = new ArrayList<>();
        edglQueryResList = new ArrayList<>();
        eddyQueryResList = new ArrayList<>();
        resQueryList = new ArrayList<>();


        Log.d("asd", "djxhStr: " + djxhStr);
        Log.d("asd", "edglStr: " + edglStr);
        Log.d("asd", "eddyStr: " + eddyStr);

        for (IMotorBean imb :allIMotorBean) {

            if (djxhStr!=null && (imb.getDjLibName().indexOf(djxhStr.toString()) != -1 || characterParser.getSelling(imb.getDjLibName()).startsWith(djxhStr.toString()) )){
                djxhQueryResList.add(imb);
            }

            if (edglStr!=null && (imb.getEdgl().indexOf(edglStr.toString()) != -1 || characterParser.getSelling(imb.getEdgl()).startsWith(edglStr.toString()))){
                edglQueryResList.add(imb);
            }

            if (eddyStr!=null && (imb.getEddy().indexOf(eddyStr.toString()) != -1 || characterParser.getSelling(imb.getEddy()).startsWith(eddyStr.toString()))) {
                eddyQueryResList.add(imb);
            }
        }

        Log.d("asd", "djxhQueryResList.size(): " + djxhQueryResList.size());
        Log.d("asd", "edglQueryResList.size(): " + edglQueryResList.size());
        Log.d("asd", "eddyQueryResList.size(): " + eddyQueryResList.size());

        if (djxhStr!=null){
            if (resQueryList.size()==0 && djxhQueryResList.size()!=0){
                resQueryList.addAll(djxhQueryResList);
            }else if (resQueryList.size()!=0 && djxhQueryResList.size() == 0){
                resQueryList.retainAll(djxhQueryResList);
            }
        }

        Log.d("asd", "getResList111: " + resQueryList.size());

        if (edglStr!=null){
            if (resQueryList.size()==0 && edglQueryResList.size() !=0){
                resQueryList.addAll(edglQueryResList);
            }else {
                resQueryList.retainAll(edglQueryResList);
            }
        }

        Log.d("asd", "getResList222: " + resQueryList.size());
        Log.d("asd", "eddyStr: " + eddyStr);

        if (!eddyStr.equals("-1") ){
            if (resQueryList.size()==0 && eddyQueryResList.size()!=0){
                resQueryList.addAll(eddyQueryResList);
            }else {
                resQueryList.retainAll(eddyQueryResList);
            }
        }
        Log.d("asd", "getResList333: " + resQueryList.size());
    }

    @Override
    public List<IMotorBean> getResQueryList() {
        return resQueryList;
    }

}
