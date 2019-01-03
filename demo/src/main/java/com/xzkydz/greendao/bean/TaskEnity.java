package com.xzkydz.greendao.bean;


import com.xzkydz.function.search.module.ITaskRoot;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

//implements ITaskRoot

@Entity
public class TaskEnity implements ITaskRoot {

//    @Entity：告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
//    @Id：对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
//    @Property：可以自定义字段名，注意外键不能使用该属性
//    @NotNull：属性不能为空
//    @Transient：使用该注释的属性不会被存入数据库的字段中
//    @Unique：该属性值必须在数据库中是唯一值
//    @Generated：编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改

    //不能用int
    @Id(autoincrement = true)
    private Long id;
    //受检单位名称
    private String unitName;
    //瓦斯泵编号
    private String gasePumpNumber;
    //测试员
    private String peopleName;
    //测试任务的状态
    private boolean _IsCompleteTask;
    //测试任务已经测得测试数据条数
    private int taskHaveGetData;
    //测试任务的创建时间
    private String greateTaskTime;
    //测试类型： 主管路 -- 支管路
    private boolean _IsMainPipeLine;
    //流量计类型
    private String lljlx;
    //流量计系数
    private String lljxs;
    //流体密度
    private String ltmd;
    //管道内经
    private String gdnjPtg;
    //电机型号
    private String djxh;
    //钳表量程
    private String qblc;
    //测试方法
    private String csff;
    //传动效率
    private String cdxl;
    //电压变比1
    private String dybbOne;
    //电压变比2
    private String dybbTwo;
    //电流变比1
    private String dlbbOne;
    //电流变比2
    private String dlbbTwo;

    private String motor_eddy;
    private String motor_eddl;
    private String motor_edgl;
    private String motor_edxl;
    private String motor_kzdl;
    private String motor_kzgl;
    private String motor_edglys;
    private String motor_js;
    private String motor_wgjjdl;
    private String motor_IsChecked;


    private boolean _IsInput;
    // 输入电机功率
    private boolean _IsInputDjgl;
    private String inputDjgl;

    // 输入气体温度
    private boolean _IsInputQtwd;
    private String inputQtwd;

    // 输入进气绝压
    private boolean _IsInputJqjy;
    private String inputJqjy;

    //输入负压
    private boolean _IsInputFy;
    private String inputFy;

    //输入管道绝压
    private boolean _IsInputGdjy;
    private String inputGdjy;

    //输入差压
    private boolean _IsInputCy;
    private String inputCy;

    //输入氧气浓度
    private boolean _IsInputYqnd;
    private String inputYqnd;

    //输入一氧化碳浓度
    private boolean _IsInputYyhtnd;
    private String inputYyhtnd;

    //输入甲烷浓度
    private boolean _IsInputJwnd;
    private String inputJwnd;

    //输入二氧化碳浓度
    private boolean _IsInputEyhtnd;
    private String inputEyhtnd;
    // 管道内经 ： 支管路
    private String gdnjZgl;

    //
    @Generated(hash = 1369375692)
    public TaskEnity(Long id, String unitName, String gasePumpNumber,
                     String peopleName, boolean _IsCompleteTask, int taskHaveGetData,
                     String greateTaskTime, boolean _IsMainPipeLine, String lljlx,
                     String lljxs, String ltmd, String gdnjPtg, String djxh, String qblc,
                     String csff, String cdxl, String dybbOne, String dybbTwo,
                     String dlbbOne, String dlbbTwo, String motor_eddy, String motor_eddl,
                     String motor_edgl, String motor_edxl, String motor_kzdl,
                     String motor_kzgl, String motor_edglys, String motor_js,
                     String motor_wgjjdl, String motor_IsChecked, boolean _IsInput,
                     boolean _IsInputDjgl, String inputDjgl, boolean _IsInputQtwd,
                     String inputQtwd, boolean _IsInputJqjy, String inputJqjy,
                     boolean _IsInputFy, String inputFy, boolean _IsInputGdjy,
                     String inputGdjy, boolean _IsInputCy, String inputCy,
                     boolean _IsInputYqnd, String inputYqnd, boolean _IsInputYyhtnd,
                     String inputYyhtnd, boolean _IsInputJwnd, String inputJwnd,
                     boolean _IsInputEyhtnd, String inputEyhtnd, String gdnjZgl) {
        this.id = id;
        this.unitName = unitName;
        this.gasePumpNumber = gasePumpNumber;
        this.peopleName = peopleName;
        this._IsCompleteTask = _IsCompleteTask;
        this.taskHaveGetData = taskHaveGetData;
        this.greateTaskTime = greateTaskTime;
        this._IsMainPipeLine = _IsMainPipeLine;
        this.lljlx = lljlx;
        this.lljxs = lljxs;
        this.ltmd = ltmd;
        this.gdnjPtg = gdnjPtg;
        this.djxh = djxh;
        this.qblc = qblc;
        this.csff = csff;
        this.cdxl = cdxl;
        this.dybbOne = dybbOne;
        this.dybbTwo = dybbTwo;
        this.dlbbOne = dlbbOne;
        this.dlbbTwo = dlbbTwo;
        this.motor_eddy = motor_eddy;
        this.motor_eddl = motor_eddl;
        this.motor_edgl = motor_edgl;
        this.motor_edxl = motor_edxl;
        this.motor_kzdl = motor_kzdl;
        this.motor_kzgl = motor_kzgl;
        this.motor_edglys = motor_edglys;
        this.motor_js = motor_js;
        this.motor_wgjjdl = motor_wgjjdl;
        this.motor_IsChecked = motor_IsChecked;
        this._IsInput = _IsInput;
        this._IsInputDjgl = _IsInputDjgl;
        this.inputDjgl = inputDjgl;
        this._IsInputQtwd = _IsInputQtwd;
        this.inputQtwd = inputQtwd;
        this._IsInputJqjy = _IsInputJqjy;
        this.inputJqjy = inputJqjy;
        this._IsInputFy = _IsInputFy;
        this.inputFy = inputFy;
        this._IsInputGdjy = _IsInputGdjy;
        this.inputGdjy = inputGdjy;
        this._IsInputCy = _IsInputCy;
        this.inputCy = inputCy;
        this._IsInputYqnd = _IsInputYqnd;
        this.inputYqnd = inputYqnd;
        this._IsInputYyhtnd = _IsInputYyhtnd;
        this.inputYyhtnd = inputYyhtnd;
        this._IsInputJwnd = _IsInputJwnd;
        this.inputJwnd = inputJwnd;
        this._IsInputEyhtnd = _IsInputEyhtnd;
        this.inputEyhtnd = inputEyhtnd;
        this.gdnjZgl = gdnjZgl;
    }

    @Generated(hash = 694408341)
    public TaskEnity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getTaskId() {
        return id;
    }

    public String getUnitName() {
        return this.unitName;
    }

    @Override
    public String getTestFunction() {
        return _IsMainPipeLine ? "主管路、泵" : "支管路";
    }

    @Override
    public String getNumber() {
        return gasePumpNumber;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGasePumpNumber() {
        return this.gasePumpNumber;
    }

    public void setGasePumpNumber(String gasePumpNumber) {
        this.gasePumpNumber = gasePumpNumber;
    }

    public String getPeopleName() {
        return this.peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public boolean get_IsCompleteTask() {
        return this._IsCompleteTask;
    }

    public void set_IsCompleteTask(boolean _IsCompleteTask) {
        this._IsCompleteTask = _IsCompleteTask;
    }

    public int getTaskHaveGetData() {
        return this.taskHaveGetData;
    }

    @Override
    public boolean getTaskStatue() {
        return _IsCompleteTask;
    }

    public void setTaskHaveGetData(int taskHaveGetData) {
        this.taskHaveGetData = taskHaveGetData;
    }

    public String getGreateTaskTime() {
        return this.greateTaskTime;
    }

    public void setGreateTaskTime(String greateTaskTime) {
        this.greateTaskTime = greateTaskTime;
    }

    public boolean get_IsMainPipeLine() {
        return this._IsMainPipeLine;
    }

    public void set_IsMainPipeLine(boolean _IsMainPipeLine) {
        this._IsMainPipeLine = _IsMainPipeLine;
    }

    public String getLljlx() {
        return this.lljlx;
    }

    public void setLljlx(String lljlx) {
        this.lljlx = lljlx;
    }

    public String getLljxs() {
        return this.lljxs;
    }

    public void setLljxs(String lljxs) {
        this.lljxs = lljxs;
    }

    public String getLtmd() {
        return this.ltmd;
    }

    public void setLtmd(String ltmd) {
        this.ltmd = ltmd;
    }

    public String getGdnjPtg() {
        return this.gdnjPtg;
    }

    public void setGdnjPtg(String gdnjPtg) {
        this.gdnjPtg = gdnjPtg;
    }

    public String getDjxh() {
        return this.djxh;
    }

    public void setDjxh(String djxh) {
        this.djxh = djxh;
    }

    public String getQblc() {
        return this.qblc;
    }

    public void setQblc(String qblc) {
        this.qblc = qblc;
    }

    public String getCsff() {
        return this.csff;
    }

    public void setCsff(String csff) {
        this.csff = csff;
    }

    public String getCdxl() {
        return this.cdxl;
    }

    public void setCdxl(String cdxl) {
        this.cdxl = cdxl;
    }

    public String getDybbOne() {
        return this.dybbOne;
    }

    public void setDybbOne(String dybbOne) {
        this.dybbOne = dybbOne;
    }

    public String getDybbTwo() {
        return this.dybbTwo;
    }

    public void setDybbTwo(String dybbTwo) {
        this.dybbTwo = dybbTwo;
    }

    public String getDlbbOne() {
        return this.dlbbOne;
    }

    public void setDlbbOne(String dlbbOne) {
        this.dlbbOne = dlbbOne;
    }

    public String getDlbbTwo() {
        return this.dlbbTwo;
    }

    public void setDlbbTwo(String dlbbTwo) {
        this.dlbbTwo = dlbbTwo;
    }

    public String getMotor_eddy() {
        return this.motor_eddy;
    }

    public void setMotor_eddy(String motor_eddy) {
        this.motor_eddy = motor_eddy;
    }

    public String getMotor_eddl() {
        return this.motor_eddl;
    }

    public void setMotor_eddl(String motor_eddl) {
        this.motor_eddl = motor_eddl;
    }

    public String getMotor_edgl() {
        return this.motor_edgl;
    }

    public void setMotor_edgl(String motor_edgl) {
        this.motor_edgl = motor_edgl;
    }

    public String getMotor_edxl() {
        return this.motor_edxl;
    }

    public void setMotor_edxl(String motor_edxl) {
        this.motor_edxl = motor_edxl;
    }

    public String getMotor_kzdl() {
        return this.motor_kzdl;
    }

    public void setMotor_kzdl(String motor_kzdl) {
        this.motor_kzdl = motor_kzdl;
    }

    public String getMotor_kzgl() {
        return this.motor_kzgl;
    }

    public void setMotor_kzgl(String motor_kzgl) {
        this.motor_kzgl = motor_kzgl;
    }

    public String getMotor_edglys() {
        return this.motor_edglys;
    }

    public void setMotor_edglys(String motor_edglys) {
        this.motor_edglys = motor_edglys;
    }

    public String getMotor_js() {
        return this.motor_js;
    }

    public void setMotor_js(String motor_js) {
        this.motor_js = motor_js;
    }

    public String getMotor_wgjjdl() {
        return this.motor_wgjjdl;
    }

    public void setMotor_wgjjdl(String motor_wgjjdl) {
        this.motor_wgjjdl = motor_wgjjdl;
    }

    public String getMotor_IsChecked() {
        return this.motor_IsChecked;
    }

    public void setMotor_IsChecked(String motor_IsChecked) {
        this.motor_IsChecked = motor_IsChecked;
    }

    public boolean get_IsInput() {
        return this._IsInput;
    }

    public void set_IsInput(boolean _IsInput) {
        this._IsInput = _IsInput;
    }

    public boolean get_IsInputDjgl() {
        return this._IsInputDjgl;
    }

    public void set_IsInputDjgl(boolean _IsInputDjgl) {
        this._IsInputDjgl = _IsInputDjgl;
    }

    public String getInputDjgl() {
        return this.inputDjgl;
    }

    public void setInputDjgl(String inputDjgl) {
        this.inputDjgl = inputDjgl;
    }

    public boolean get_IsInputQtwd() {
        return this._IsInputQtwd;
    }

    public void set_IsInputQtwd(boolean _IsInputQtwd) {
        this._IsInputQtwd = _IsInputQtwd;
    }

    public String getInputQtwd() {
        return this.inputQtwd;
    }

    public void setInputQtwd(String inputQtwd) {
        this.inputQtwd = inputQtwd;
    }

    public boolean get_IsInputJqjy() {
        return this._IsInputJqjy;
    }

    public void set_IsInputJqjy(boolean _IsInputJqjy) {
        this._IsInputJqjy = _IsInputJqjy;
    }

    public String getInputJqjy() {
        return this.inputJqjy;
    }

    public void setInputJqjy(String inputJqjy) {
        this.inputJqjy = inputJqjy;
    }

    public boolean get_IsInputFy() {
        return this._IsInputFy;
    }

    public void set_IsInputFy(boolean _IsInputFy) {
        this._IsInputFy = _IsInputFy;
    }

    public String getInputFy() {
        return this.inputFy;
    }

    public void setInputFy(String inputFy) {
        this.inputFy = inputFy;
    }

    public boolean get_IsInputGdjy() {
        return this._IsInputGdjy;
    }

    public void set_IsInputGdjy(boolean _IsInputGdjy) {
        this._IsInputGdjy = _IsInputGdjy;
    }

    public String getInputGdjy() {
        return this.inputGdjy;
    }

    public void setInputGdjy(String inputGdjy) {
        this.inputGdjy = inputGdjy;
    }

    public boolean get_IsInputCy() {
        return this._IsInputCy;
    }

    public void set_IsInputCy(boolean _IsInputCy) {
        this._IsInputCy = _IsInputCy;
    }

    public String getInputCy() {
        return this.inputCy;
    }

    public void setInputCy(String inputCy) {
        this.inputCy = inputCy;
    }

    public boolean get_IsInputYqnd() {
        return this._IsInputYqnd;
    }

    public void set_IsInputYqnd(boolean _IsInputYqnd) {
        this._IsInputYqnd = _IsInputYqnd;
    }

    public String getInputYqnd() {
        return this.inputYqnd;
    }

    public void setInputYqnd(String inputYqnd) {
        this.inputYqnd = inputYqnd;
    }

    public boolean get_IsInputYyhtnd() {
        return this._IsInputYyhtnd;
    }

    public void set_IsInputYyhtnd(boolean _IsInputYyhtnd) {
        this._IsInputYyhtnd = _IsInputYyhtnd;
    }

    public String getInputYyhtnd() {
        return this.inputYyhtnd;
    }

    public void setInputYyhtnd(String inputYyhtnd) {
        this.inputYyhtnd = inputYyhtnd;
    }

    public boolean get_IsInputJwnd() {
        return this._IsInputJwnd;
    }

    public void set_IsInputJwnd(boolean _IsInputJwnd) {
        this._IsInputJwnd = _IsInputJwnd;
    }

    public String getInputJwnd() {
        return this.inputJwnd;
    }

    public void setInputJwnd(String inputJwnd) {
        this.inputJwnd = inputJwnd;
    }

    public boolean get_IsInputEyhtnd() {
        return this._IsInputEyhtnd;
    }

    public void set_IsInputEyhtnd(boolean _IsInputEyhtnd) {
        this._IsInputEyhtnd = _IsInputEyhtnd;
    }

    public String getInputEyhtnd() {
        return this.inputEyhtnd;
    }

    public void setInputEyhtnd(String inputEyhtnd) {
        this.inputEyhtnd = inputEyhtnd;
    }

    public String getGdnjZgl() {
        return this.gdnjZgl;
    }

    public void setGdnjZgl(String gdnjZgl) {
        this.gdnjZgl = gdnjZgl;
    }


}
