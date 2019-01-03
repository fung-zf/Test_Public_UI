package com.xzkydz.function.main.style;


import com.xzkydz.function.main.NavigationActivity;

/**
 * 用于管理Activity的外观
 */
public interface NavigationActivityManagerInf {

    /**
     * 设置侧边栏显示“打印”Item
     */
    NavigationActivityManagerInf setShowPrints(boolean showPrints);

    /**
     * 显示配置项
     * @param show
     * @return
     */
    NavigationActivityManagerInf setShowAllConfig(boolean show);

     /**
      * 设置显示配置Item
      */
     NavigationActivityManagerInf setShowConfiguration(boolean showConfiguration);

    /**
     * 设置侧边栏头部背景图
     */
    NavigationActivityManagerInf setNavigationViewHeardBg(int drawableId);

    /**
     * 设置主界面背景图
     */
    NavigationActivityManagerInf setNavigationActivityBg(int drawableId);

    /**
     * 设置主界面Button的背景，按从左到右、从上到下编号，数组位置和按钮对应。
     */
    NavigationActivityManagerInf setMainButtonBg(int[] bgArray);

    /**
     * 设置App name
     */
    NavigationActivityManagerInf setAppName(int appName);

     /**
      * 侧边栏的字体颜色
      */
     NavigationActivityManagerInf setLeftItemTextColor(int colorId);

     /**
      * 顶部公司名称、网址的颜色
      */
     NavigationActivityManagerInf setLeftHeadTextColor(int colorId);

     /**
      * 设置界面底部 ImageView的SRC背景图
      */
     NavigationActivityManagerInf setMainBottomImageSrc(int drawableId);

     boolean getShowPrints();

     boolean getShowConfiguration();

     boolean getShowConfigAllItem();

     int getNavigationViewHeardBg();

     int getNavigationActivityBg();

     int[] getMainButtonBg();

     int getAppName();

     int getLeftItemTextColor();

     int getLeftHeadTextColor();

     int getMainBottomImageSrc();


}
