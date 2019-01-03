package com.xzkydz.function.pdf;

public class PdfInfBean {

    private String nameStr;
    private String urlStr;

    public String getNameStr() {
        return nameStr;
    }

    public PdfInfBean(String nameStr, String urlStr) {
        this.nameStr = nameStr;
        this.urlStr = urlStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }
}
