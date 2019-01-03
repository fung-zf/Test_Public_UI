package com.xzkydz.function.gaspublicfunction;

import com.xzkydz.function.motor.module.IMotorBean;

public class MotorBean implements IMotorBean {

    private Long id = 10L;
    private String djxlStr = "系列0000";
    private String djxhStr = "测试型号一";
    private String eddyStr = "1500V";
    private String eddlStr = "100 A";
    private String edglStr = "1230Kw";
    private String edxlStr = "98%";
    private String kzdlStr = "10A";
    private String kzglStr = "100Kw";
    private String edglysStr = "1";
    private String jsStr = "10";
    private String wgjjdlStr = "12";

    public String getDjxlStr() {
        return djxlStr;
    }

    public void setDjxlStr(String djxlStr) {
        this.djxlStr = djxlStr;
    }

    private boolean isAdd = false;


    public void setId(Long id) {
        this.id = id;
    }

    public void setDjxhStr(String djxhStr) {
        this.djxhStr = djxhStr;
    }

    public void setEddyStr(String eddyStr) {
        this.eddyStr = eddyStr;
    }

    public void setEddlStr(String eddlStr) {
        this.eddlStr = eddlStr;
    }

    public void setEdglStr(String edglStr) {
        this.edglStr = edglStr;
    }

    public void setEdxlStr(String edxlStr) {
        this.edxlStr = edxlStr;
    }

    public void setKzdlStr(String kzdlStr) {
        this.kzdlStr = kzdlStr;
    }

    public void setKzglStr(String kzglStr) {
        this.kzglStr = kzglStr;
    }

    public void setEdglysStr(String edglysStr) {
        this.edglysStr = edglysStr;
    }

    public void setJsStr(String jsStr) {
        this.jsStr = jsStr;
    }

    public void setWgjjdlStr(String wgjjdlStr) {
        this.wgjjdlStr = wgjjdlStr;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getDjxl() {
        return djxlStr;
    }

    @Override
    public String getDjLibName() {
        return djxhStr;
    }

    @Override
    public String getEddy() {
        return eddyStr;
    }

    @Override
    public String getEddl() {
        return eddlStr;
    }

    @Override
    public String getEdgl() {
        return edglStr;
    }

    @Override
    public String getEdxl() {
        return edxlStr;
    }

    @Override
    public String getKzdl() {
        return kzdlStr;
    }

    @Override
    public String getKzgl() {
        return kzglStr;
    }

    @Override
    public String getEdglys() {
        return edglysStr;
    }

    @Override
    public String getJs() {
        return jsStr;
    }

    @Override
    public String getWgjjdl() {
        return wgjjdlStr;
    }

    @Override
    public Boolean getIsAdd() {
        return isAdd;
    }
}
