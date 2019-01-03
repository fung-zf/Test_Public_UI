package com.xzkydz.function.main.bean;


import android.content.Context;

public class LeftItemBean {

    private Context context;
    private int tittleStr ;
    private int nameStr ;
    private int iconId ;
    private int icArrowId;
    private boolean isShowTittle = false;
    private boolean isShowSplite = false;

    public LeftItemBean(Context context , int tittleStr, int nameStr, int iconId, int icArrowId ,boolean isShowTittle , boolean isShowSplite) {
        this.context = context;
        this.tittleStr = tittleStr;
        this.nameStr = nameStr;
        this.iconId = iconId;
        this.icArrowId = icArrowId;
        this.isShowTittle = isShowTittle;
        this.isShowSplite = isShowSplite;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getTittleStr() {
        return tittleStr;
    }

    public void setTittleStr(int tittleStr) {
        this.tittleStr = tittleStr;
    }

    public int getNameStr() {
        return nameStr;
    }

    public void setNameStr(int nameStr) {
        this.nameStr = nameStr;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getIcArrowId() {
        return icArrowId;
    }

    public void setIcArrowId(int icArrowId) {
        this.icArrowId = icArrowId;
    }

    public boolean isShowTittle() {
        return isShowTittle;
    }

    public void setShowTittle(boolean showTittle) {
        isShowTittle = showTittle;
    }

    public boolean isShowSplite() {
        return isShowSplite;
    }

    public void setShowSplite(boolean showSplite) {
        isShowSplite = showSplite;
    }
}
