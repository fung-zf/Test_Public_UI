package com.xzkydz.function.main.style;

import com.xzkydz.function.style.AppStyle;

/**
 * 主页样式设置
 */

public class NavigationActivityManager implements NavigationActivityManagerInf {


    @Override
    public NavigationActivityManagerInf setShowPrints(boolean showPrints) {
        NavigationActivityStyle.isShowPrints = showPrints;
        return this;
    }

    @Override
    public NavigationActivityManagerInf setShowAllConfig(boolean show) {
        NavigationActivityStyle.isShowAllConfig = show;
        return this;
    }

    @Override
    public NavigationActivityManagerInf setShowConfiguration(boolean showConfiguration) {
        NavigationActivityStyle.isShowConfiguration = showConfiguration;
        return this;
    }

    @Override
    public NavigationActivityManagerInf setNavigationViewHeardBg(int drawableId) {
        NavigationActivityStyle.navigationHeardBGId = drawableId;
        return this;
    }

    @Override
    public NavigationActivityManagerInf setNavigationActivityBg(int drawableId) {
        NavigationActivityStyle.navigationActivityBgID = drawableId;
        return this;
    }

    @Override
    public NavigationActivityManagerInf setMainButtonBg(int[] bgArray) {
        NavigationActivityStyle.bgArray = bgArray;
        return this;
    }

    @Override
    public NavigationActivityManagerInf setAppName(int appName) {
         AppStyle.appNameId = appName;
        return this;
    }

    @Override
    public NavigationActivityManagerInf setLeftItemTextColor(int colorId) {
        NavigationActivityStyle.leftItemTextColor = colorId;
        return this;
    }

    @Override
    public NavigationActivityManagerInf setLeftHeadTextColor(int colorId) {
        NavigationActivityStyle.leftHeadTextColor = colorId;
        return this;
    }

    @Override
    public NavigationActivityManagerInf setMainBottomImageSrc(int drawableId) {
        return this;
    }

    @Override
    public boolean getShowPrints() {
        return NavigationActivityStyle.isShowPrints;
    }

    @Override
    public boolean getShowConfiguration() {
        return NavigationActivityStyle.isShowConfiguration;
    }

    @Override
    public boolean getShowConfigAllItem() {
        return NavigationActivityStyle.isShowAllConfig;
    }

    @Override
    public int getNavigationViewHeardBg() {
        return NavigationActivityStyle.navigationHeardBGId;
    }

    @Override
    public int getNavigationActivityBg() {
        return NavigationActivityStyle.navigationActivityBgID;
    }

    @Override
    public int[] getMainButtonBg() {
        return NavigationActivityStyle.bgArray;
    }

    @Override
    public int getAppName() {
        return AppStyle.appNameId;
    }

    @Override
    public int getLeftItemTextColor() {
        return NavigationActivityStyle.leftItemTextColor;
    }

    @Override
    public int getLeftHeadTextColor() {
        return NavigationActivityStyle.leftHeadTextColor;
    }

    @Override
    public int getMainBottomImageSrc() {
        return NavigationActivityStyle.navigationActivityBottomImage;
    }

}
