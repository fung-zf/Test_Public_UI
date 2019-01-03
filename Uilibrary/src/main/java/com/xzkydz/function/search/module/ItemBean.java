package com.xzkydz.function.search.module;

/*
 * create at 2018/5/7
 * description:单条详细参数的ITem
 */
public class ItemBean {

    private String nameStr;
    private String contentStr;


    public String getNameStr() {
        return nameStr;
    }

    public ItemBean setNameStr(String nameStr) {
        this.nameStr = nameStr;
        return this;
    }

    public String getContentStr() {
        return contentStr;
    }

    public ItemBean setContentStr(String contentStr) {
        this.contentStr = contentStr;
        return  this;
    }
}
