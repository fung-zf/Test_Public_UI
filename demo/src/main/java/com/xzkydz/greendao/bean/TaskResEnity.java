package com.xzkydz.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class TaskResEnity {

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

    private long taskId;

    private String wdStr = "--"; //环境温度
    private String sdStr = "--"; //环境湿度
    private String dqyStr = "--"; //环境大气压
    private String timeStr = "--"; //采集的时间
    private String testFunctionStr = "--"; // 皮托管法、V锥法、孔板法、支管路

    // 电参数
    @Unique
    private String abV = "--";
    private String aA = "--";
    private String bcV = "--";
    private String bA = "--";
    private String caV = "--";
    private String cA = "--";
    private String pjV = "--";
    private String pjA = "--";
    private String djgl = "--";
    private String zgl = "--";
    private String scgl = "--";
    private String djxl = "--";
    private String zhxl = "--";
    private String glys = "--";
    private String fzxs = "--";
    private String yxzt = "--";

    // 皮托管 工况
    private String ll_PTG = "--"; //流量
    private String eyht_PTG = "--"; //二氧化碳
    private String yyht_PTG = "--"; //一氧化碳
    private String yq_PTG = "--"; //氧气
    private String jw_PTG = "--"; //甲烷
    private String dqy_PTG = "--"; //大气压
    private String jkjy_PTG = "--"; //进口绝压
    private String ckjy_PTG = "--"; //出口绝压
    private String gdfy_PTG = "--"; //管道负压
    private String gdjy_PTG = "--"; //管道绝压
    private String gdcy_PTG = "--"; //管道差压
    private String zs_PTG = "--";  //转速
    private String qtwd_PTG = "--"; //气体温度

    // 皮托管 规定条件
    private String llQGDTJ_PTG = "--";  //气规定条件下 流量
    private String llBGDTJ_PTG = "--"; //泵规定条件下 流量
    private String zglBGDTJ_PTG = "--"; //泵规定条件下  轴功率
    private String jxzkdBGDTJ_PTG = "--"; //泵规定条件下 极限真空度


    // 孔板法 V锥法 泵参数
    private String jkjyBCS_KB = "--"; //进口绝压
    private String ckjyBCS_KB = "--"; //出口绝压
    private String zsBCS_KB = "--";//转速
    private String jswdBCS_KB = "--"; //进水温度

    // 孔板法 V锥法 流量
    private String gdfy_KBVZh = "--"; //管道负压
    private String gdjy_KBVZh = "--"; //管道绝压
    private String gdcy_KBVZh = "--"; //差压
    private String dqy_KBVZh = "--"; //大气压
    private String ll_KBVZh = "--"; //流量

    // 孔板法 V锥法 浓度
    private String jw_KBVZh = "--"; //甲烷浓度
    private String eyht_KBVZh = "--"; //二氧化碳浓度
    private String yyht_KBVZh = "--"; //一氧化碳浓度
    private String yq_KBVZh = "--"; //氧气浓度
    private String qtwd_KBVZh = "--"; //气体温度

    //孔板法 V锥法  规定条件
    private String llQGDTJ_KBVZh = "--";  // 气规定条件下的流量
    private String llBGDTJ_KBVZh = "--"; //泵规定条件下的流量
    private String zglBGDTJ_KBVZh = "--"; //泵规定条件下的轴功率
    private String jxzkdBGDTJ_KBVZh = "--"; // 泵规定条件下的极限真空度

    //支管路 测试数据
    private String eyht_ZHGL = "--"; //二氧化碳
    private String yyht_ZHGL = "--"; //一氧化碳
    private String yq_ZHGL = "--"; //氧气
    private String jw_ZHGL = "--"; //甲烷
    private String gdfy_ZHGL = "--"; //管道负压
    private String gdjy_ZHGL = "--"; //管道绝压
    private String gdcy_ZHGL = "--"; //管道差压
    private String ll_ZHGL = "--"; //支管路
    private String qtwd_ZHGL = "--"; /*气体温度*/
    @Generated(hash = 1839768380)
    public TaskResEnity(Long id, long taskId, String wdStr, String sdStr, String dqyStr,
            String timeStr, String testFunctionStr, String abV, String aA, String bcV,
            String bA, String caV, String cA, String pjV, String pjA, String djgl, String zgl,
            String scgl, String djxl, String zhxl, String glys, String fzxs, String yxzt,
            String ll_PTG, String eyht_PTG, String yyht_PTG, String yq_PTG, String jw_PTG,
            String dqy_PTG, String jkjy_PTG, String ckjy_PTG, String gdfy_PTG,
            String gdjy_PTG, String gdcy_PTG, String zs_PTG, String qtwd_PTG,
            String llQGDTJ_PTG, String llBGDTJ_PTG, String zglBGDTJ_PTG,
            String jxzkdBGDTJ_PTG, String jkjyBCS_KB, String ckjyBCS_KB, String zsBCS_KB,
            String jswdBCS_KB, String gdfy_KBVZh, String gdjy_KBVZh, String gdcy_KBVZh,
            String dqy_KBVZh, String ll_KBVZh, String jw_KBVZh, String eyht_KBVZh,
            String yyht_KBVZh, String yq_KBVZh, String qtwd_KBVZh, String llQGDTJ_KBVZh,
            String llBGDTJ_KBVZh, String zglBGDTJ_KBVZh, String jxzkdBGDTJ_KBVZh,
            String eyht_ZHGL, String yyht_ZHGL, String yq_ZHGL, String jw_ZHGL,
            String gdfy_ZHGL, String gdjy_ZHGL, String gdcy_ZHGL, String ll_ZHGL,
            String qtwd_ZHGL) {
        this.id = id;
        this.taskId = taskId;
        this.wdStr = wdStr;
        this.sdStr = sdStr;
        this.dqyStr = dqyStr;
        this.timeStr = timeStr;
        this.testFunctionStr = testFunctionStr;
        this.abV = abV;
        this.aA = aA;
        this.bcV = bcV;
        this.bA = bA;
        this.caV = caV;
        this.cA = cA;
        this.pjV = pjV;
        this.pjA = pjA;
        this.djgl = djgl;
        this.zgl = zgl;
        this.scgl = scgl;
        this.djxl = djxl;
        this.zhxl = zhxl;
        this.glys = glys;
        this.fzxs = fzxs;
        this.yxzt = yxzt;
        this.ll_PTG = ll_PTG;
        this.eyht_PTG = eyht_PTG;
        this.yyht_PTG = yyht_PTG;
        this.yq_PTG = yq_PTG;
        this.jw_PTG = jw_PTG;
        this.dqy_PTG = dqy_PTG;
        this.jkjy_PTG = jkjy_PTG;
        this.ckjy_PTG = ckjy_PTG;
        this.gdfy_PTG = gdfy_PTG;
        this.gdjy_PTG = gdjy_PTG;
        this.gdcy_PTG = gdcy_PTG;
        this.zs_PTG = zs_PTG;
        this.qtwd_PTG = qtwd_PTG;
        this.llQGDTJ_PTG = llQGDTJ_PTG;
        this.llBGDTJ_PTG = llBGDTJ_PTG;
        this.zglBGDTJ_PTG = zglBGDTJ_PTG;
        this.jxzkdBGDTJ_PTG = jxzkdBGDTJ_PTG;
        this.jkjyBCS_KB = jkjyBCS_KB;
        this.ckjyBCS_KB = ckjyBCS_KB;
        this.zsBCS_KB = zsBCS_KB;
        this.jswdBCS_KB = jswdBCS_KB;
        this.gdfy_KBVZh = gdfy_KBVZh;
        this.gdjy_KBVZh = gdjy_KBVZh;
        this.gdcy_KBVZh = gdcy_KBVZh;
        this.dqy_KBVZh = dqy_KBVZh;
        this.ll_KBVZh = ll_KBVZh;
        this.jw_KBVZh = jw_KBVZh;
        this.eyht_KBVZh = eyht_KBVZh;
        this.yyht_KBVZh = yyht_KBVZh;
        this.yq_KBVZh = yq_KBVZh;
        this.qtwd_KBVZh = qtwd_KBVZh;
        this.llQGDTJ_KBVZh = llQGDTJ_KBVZh;
        this.llBGDTJ_KBVZh = llBGDTJ_KBVZh;
        this.zglBGDTJ_KBVZh = zglBGDTJ_KBVZh;
        this.jxzkdBGDTJ_KBVZh = jxzkdBGDTJ_KBVZh;
        this.eyht_ZHGL = eyht_ZHGL;
        this.yyht_ZHGL = yyht_ZHGL;
        this.yq_ZHGL = yq_ZHGL;
        this.jw_ZHGL = jw_ZHGL;
        this.gdfy_ZHGL = gdfy_ZHGL;
        this.gdjy_ZHGL = gdjy_ZHGL;
        this.gdcy_ZHGL = gdcy_ZHGL;
        this.ll_ZHGL = ll_ZHGL;
        this.qtwd_ZHGL = qtwd_ZHGL;
    }
    @Generated(hash = 131024579)
    public TaskResEnity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getWdStr() {
        return this.wdStr;
    }
    public void setWdStr(String wdStr) {
        this.wdStr = wdStr;
    }
    public String getSdStr() {
        return this.sdStr;
    }
    public void setSdStr(String sdStr) {
        this.sdStr = sdStr;
    }
    public String getDqyStr() {
        return this.dqyStr;
    }
    public void setDqyStr(String dqyStr) {
        this.dqyStr = dqyStr;
    }
    public String getTimeStr() {
        return this.timeStr;
    }
    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }
    public String getTestFunctionStr() {
        return this.testFunctionStr;
    }
    public void setTestFunctionStr(String testFunctionStr) {
        this.testFunctionStr = testFunctionStr;
    }
    public String getAbV() {
        return this.abV;
    }
    public void setAbV(String abV) {
        this.abV = abV;
    }
    public String getAA() {
        return this.aA;
    }
    public void setAA(String aA) {
        this.aA = aA;
    }
    public String getBcV() {
        return this.bcV;
    }
    public void setBcV(String bcV) {
        this.bcV = bcV;
    }
    public String getBA() {
        return this.bA;
    }
    public void setBA(String bA) {
        this.bA = bA;
    }
    public String getCaV() {
        return this.caV;
    }
    public void setCaV(String caV) {
        this.caV = caV;
    }
    public String getCA() {
        return this.cA;
    }
    public void setCA(String cA) {
        this.cA = cA;
    }
    public String getPjV() {
        return this.pjV;
    }
    public void setPjV(String pjV) {
        this.pjV = pjV;
    }
    public String getPjA() {
        return this.pjA;
    }
    public void setPjA(String pjA) {
        this.pjA = pjA;
    }
    public String getDjgl() {
        return this.djgl;
    }
    public void setDjgl(String djgl) {
        this.djgl = djgl;
    }
    public String getZgl() {
        return this.zgl;
    }
    public void setZgl(String zgl) {
        this.zgl = zgl;
    }
    public String getScgl() {
        return this.scgl;
    }
    public void setScgl(String scgl) {
        this.scgl = scgl;
    }
    public String getDjxl() {
        return this.djxl;
    }
    public void setDjxl(String djxl) {
        this.djxl = djxl;
    }
    public String getZhxl() {
        return this.zhxl;
    }
    public void setZhxl(String zhxl) {
        this.zhxl = zhxl;
    }
    public String getGlys() {
        return this.glys;
    }
    public void setGlys(String glys) {
        this.glys = glys;
    }
    public String getFzxs() {
        return this.fzxs;
    }
    public void setFzxs(String fzxs) {
        this.fzxs = fzxs;
    }
    public String getYxzt() {
        return this.yxzt;
    }
    public void setYxzt(String yxzt) {
        this.yxzt = yxzt;
    }
    public String getLl_PTG() {
        return this.ll_PTG;
    }
    public void setLl_PTG(String ll_PTG) {
        this.ll_PTG = ll_PTG;
    }
    public String getEyht_PTG() {
        return this.eyht_PTG;
    }
    public void setEyht_PTG(String eyht_PTG) {
        this.eyht_PTG = eyht_PTG;
    }
    public String getYyht_PTG() {
        return this.yyht_PTG;
    }
    public void setYyht_PTG(String yyht_PTG) {
        this.yyht_PTG = yyht_PTG;
    }
    public String getYq_PTG() {
        return this.yq_PTG;
    }
    public void setYq_PTG(String yq_PTG) {
        this.yq_PTG = yq_PTG;
    }
    public String getJw_PTG() {
        return this.jw_PTG;
    }
    public void setJw_PTG(String jw_PTG) {
        this.jw_PTG = jw_PTG;
    }
    public String getDqy_PTG() {
        return this.dqy_PTG;
    }
    public void setDqy_PTG(String dqy_PTG) {
        this.dqy_PTG = dqy_PTG;
    }
    public String getJkjy_PTG() {
        return this.jkjy_PTG;
    }
    public void setJkjy_PTG(String jkjy_PTG) {
        this.jkjy_PTG = jkjy_PTG;
    }
    public String getCkjy_PTG() {
        return this.ckjy_PTG;
    }
    public void setCkjy_PTG(String ckjy_PTG) {
        this.ckjy_PTG = ckjy_PTG;
    }
    public String getGdfy_PTG() {
        return this.gdfy_PTG;
    }
    public void setGdfy_PTG(String gdfy_PTG) {
        this.gdfy_PTG = gdfy_PTG;
    }
    public String getGdjy_PTG() {
        return this.gdjy_PTG;
    }
    public void setGdjy_PTG(String gdjy_PTG) {
        this.gdjy_PTG = gdjy_PTG;
    }
    public String getGdcy_PTG() {
        return this.gdcy_PTG;
    }
    public void setGdcy_PTG(String gdcy_PTG) {
        this.gdcy_PTG = gdcy_PTG;
    }
    public String getZs_PTG() {
        return this.zs_PTG;
    }
    public void setZs_PTG(String zs_PTG) {
        this.zs_PTG = zs_PTG;
    }
    public String getQtwd_PTG() {
        return this.qtwd_PTG;
    }
    public void setQtwd_PTG(String qtwd_PTG) {
        this.qtwd_PTG = qtwd_PTG;
    }
    public String getLlQGDTJ_PTG() {
        return this.llQGDTJ_PTG;
    }
    public void setLlQGDTJ_PTG(String llQGDTJ_PTG) {
        this.llQGDTJ_PTG = llQGDTJ_PTG;
    }
    public String getLlBGDTJ_PTG() {
        return this.llBGDTJ_PTG;
    }
    public void setLlBGDTJ_PTG(String llBGDTJ_PTG) {
        this.llBGDTJ_PTG = llBGDTJ_PTG;
    }
    public String getZglBGDTJ_PTG() {
        return this.zglBGDTJ_PTG;
    }
    public void setZglBGDTJ_PTG(String zglBGDTJ_PTG) {
        this.zglBGDTJ_PTG = zglBGDTJ_PTG;
    }
    public String getJxzkdBGDTJ_PTG() {
        return this.jxzkdBGDTJ_PTG;
    }
    public void setJxzkdBGDTJ_PTG(String jxzkdBGDTJ_PTG) {
        this.jxzkdBGDTJ_PTG = jxzkdBGDTJ_PTG;
    }
    public String getJkjyBCS_KB() {
        return this.jkjyBCS_KB;
    }
    public void setJkjyBCS_KB(String jkjyBCS_KB) {
        this.jkjyBCS_KB = jkjyBCS_KB;
    }
    public String getCkjyBCS_KB() {
        return this.ckjyBCS_KB;
    }
    public void setCkjyBCS_KB(String ckjyBCS_KB) {
        this.ckjyBCS_KB = ckjyBCS_KB;
    }
    public String getZsBCS_KB() {
        return this.zsBCS_KB;
    }
    public void setZsBCS_KB(String zsBCS_KB) {
        this.zsBCS_KB = zsBCS_KB;
    }
    public String getJswdBCS_KB() {
        return this.jswdBCS_KB;
    }
    public void setJswdBCS_KB(String jswdBCS_KB) {
        this.jswdBCS_KB = jswdBCS_KB;
    }
    public String getGdfy_KBVZh() {
        return this.gdfy_KBVZh;
    }
    public void setGdfy_KBVZh(String gdfy_KBVZh) {
        this.gdfy_KBVZh = gdfy_KBVZh;
    }
    public String getGdjy_KBVZh() {
        return this.gdjy_KBVZh;
    }
    public void setGdjy_KBVZh(String gdjy_KBVZh) {
        this.gdjy_KBVZh = gdjy_KBVZh;
    }
    public String getGdcy_KBVZh() {
        return this.gdcy_KBVZh;
    }
    public void setGdcy_KBVZh(String gdcy_KBVZh) {
        this.gdcy_KBVZh = gdcy_KBVZh;
    }
    public String getDqy_KBVZh() {
        return this.dqy_KBVZh;
    }
    public void setDqy_KBVZh(String dqy_KBVZh) {
        this.dqy_KBVZh = dqy_KBVZh;
    }
    public String getLl_KBVZh() {
        return this.ll_KBVZh;
    }
    public void setLl_KBVZh(String ll_KBVZh) {
        this.ll_KBVZh = ll_KBVZh;
    }
    public String getJw_KBVZh() {
        return this.jw_KBVZh;
    }
    public void setJw_KBVZh(String jw_KBVZh) {
        this.jw_KBVZh = jw_KBVZh;
    }
    public String getEyht_KBVZh() {
        return this.eyht_KBVZh;
    }
    public void setEyht_KBVZh(String eyht_KBVZh) {
        this.eyht_KBVZh = eyht_KBVZh;
    }
    public String getYyht_KBVZh() {
        return this.yyht_KBVZh;
    }
    public void setYyht_KBVZh(String yyht_KBVZh) {
        this.yyht_KBVZh = yyht_KBVZh;
    }
    public String getYq_KBVZh() {
        return this.yq_KBVZh;
    }
    public void setYq_KBVZh(String yq_KBVZh) {
        this.yq_KBVZh = yq_KBVZh;
    }
    public String getQtwd_KBVZh() {
        return this.qtwd_KBVZh;
    }
    public void setQtwd_KBVZh(String qtwd_KBVZh) {
        this.qtwd_KBVZh = qtwd_KBVZh;
    }
    public String getLlQGDTJ_KBVZh() {
        return this.llQGDTJ_KBVZh;
    }
    public void setLlQGDTJ_KBVZh(String llQGDTJ_KBVZh) {
        this.llQGDTJ_KBVZh = llQGDTJ_KBVZh;
    }
    public String getLlBGDTJ_KBVZh() {
        return this.llBGDTJ_KBVZh;
    }
    public void setLlBGDTJ_KBVZh(String llBGDTJ_KBVZh) {
        this.llBGDTJ_KBVZh = llBGDTJ_KBVZh;
    }
    public String getZglBGDTJ_KBVZh() {
        return this.zglBGDTJ_KBVZh;
    }
    public void setZglBGDTJ_KBVZh(String zglBGDTJ_KBVZh) {
        this.zglBGDTJ_KBVZh = zglBGDTJ_KBVZh;
    }
    public String getJxzkdBGDTJ_KBVZh() {
        return this.jxzkdBGDTJ_KBVZh;
    }
    public void setJxzkdBGDTJ_KBVZh(String jxzkdBGDTJ_KBVZh) {
        this.jxzkdBGDTJ_KBVZh = jxzkdBGDTJ_KBVZh;
    }
    public String getEyht_ZHGL() {
        return this.eyht_ZHGL;
    }
    public void setEyht_ZHGL(String eyht_ZHGL) {
        this.eyht_ZHGL = eyht_ZHGL;
    }
    public String getYyht_ZHGL() {
        return this.yyht_ZHGL;
    }
    public void setYyht_ZHGL(String yyht_ZHGL) {
        this.yyht_ZHGL = yyht_ZHGL;
    }
    public String getYq_ZHGL() {
        return this.yq_ZHGL;
    }
    public void setYq_ZHGL(String yq_ZHGL) {
        this.yq_ZHGL = yq_ZHGL;
    }
    public String getJw_ZHGL() {
        return this.jw_ZHGL;
    }
    public void setJw_ZHGL(String jw_ZHGL) {
        this.jw_ZHGL = jw_ZHGL;
    }
    public String getGdfy_ZHGL() {
        return this.gdfy_ZHGL;
    }
    public void setGdfy_ZHGL(String gdfy_ZHGL) {
        this.gdfy_ZHGL = gdfy_ZHGL;
    }
    public String getGdjy_ZHGL() {
        return this.gdjy_ZHGL;
    }
    public void setGdjy_ZHGL(String gdjy_ZHGL) {
        this.gdjy_ZHGL = gdjy_ZHGL;
    }
    public String getGdcy_ZHGL() {
        return this.gdcy_ZHGL;
    }
    public void setGdcy_ZHGL(String gdcy_ZHGL) {
        this.gdcy_ZHGL = gdcy_ZHGL;
    }
    public String getLl_ZHGL() {
        return this.ll_ZHGL;
    }
    public void setLl_ZHGL(String ll_ZHGL) {
        this.ll_ZHGL = ll_ZHGL;
    }
    public String getQtwd_ZHGL() {
        return this.qtwd_ZHGL;
    }
    public void setQtwd_ZHGL(String qtwd_ZHGL) {
        this.qtwd_ZHGL = qtwd_ZHGL;
    }
    public long getTaskId() {
        return this.taskId;
    }
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

   
}