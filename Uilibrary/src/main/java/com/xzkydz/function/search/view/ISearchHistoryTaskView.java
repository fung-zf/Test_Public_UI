package com.xzkydz.function.search.view;

import android.widget.BaseAdapter;

public interface ISearchHistoryTaskView {


    void initView();
    /**
     *  Description: 设置左上角的箭头返回事件
     */
    void setBackUp();


    /**
     *  Description: 显示加载动画
     */
    void showLoading();

    /**
     *  Description: 隐藏加载动画
     */
    void hideLoading();

    /**
     *  Description: listView 绑定adapter
     */
    void listViewSetAdapter(BaseAdapter adapter);

}
