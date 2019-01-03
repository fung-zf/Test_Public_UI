package com.xzkydz.function.main.style;

import com.xzkydz.function.mylibrary.R;

/**
 * Date: 2018/3/16  14:46
 * Description: 主界面样式属性
 */

public  class NavigationActivityStyle {

    //是否显示配置大项
    public static boolean isShowAllConfig = true;

    // 是否显示打印item
    public static boolean isShowPrints = false;

    // 是否显示配置item
    public static boolean isShowConfiguration = false;

    // 侧边栏顶部背景图
    public static int navigationHeardBGId = R.drawable.leftbar_top_bg;

    // 主界面背景图
    public static int navigationActivityBgID = R.drawable.home_bg;

    // 主界面的底部logo图片
    public static int navigationActivityBottomImage = R.drawable.home_pic_logo;

    // 主界面六个按钮的背景图
    public static int[] bgArray = {R.drawable.selector_navi_cs,
            R.drawable.selector_navi_shjgl,
            R.drawable.selector_navi_bgchx,
            R.drawable.selector_navi_cshbzh,
            R.drawable.selector_navi_chgq,
            R.drawable.selector_navi_tc} ;

    // 侧边栏 Item 的字体颜色
    public static int leftItemTextColor = R.color.lib_main_item_name_tv;

    // 侧边栏顶部的公司名称、网址的字体颜色
    public static int leftHeadTextColor = R.color.lib_main_company_tv;


}
