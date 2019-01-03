package com.xzkydz.function.menu.markdown;

/**
 * Created by YuKun on 2018/3/15.
 */

public  class MarkdownActivityManager implements MarkDownActivityManagerIF {

    public String productInf = "markdown/ProductInf.md";

    @Override
    public void setProductInfFile(String url) {
        this.productInf = url;
    }

    @Override
    public String getProductInfFile() {
        return null;
    }

}
