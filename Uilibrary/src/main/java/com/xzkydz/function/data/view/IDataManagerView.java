package com.xzkydz.function.data.view;



public interface IDataManagerView {


    /**
     * 初始化ViewPager
     */
    void initViewPager();

    /**
     * 隐藏加载动画
     */
    void hideLoading();

    /**
     * 显示加载动画
     */
    void showLoading();
}
