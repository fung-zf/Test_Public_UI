package com.xzkydz.function.data.all.module;

import com.xzkydz.function.search.module.ITaskRoot;

import java.util.List;

public interface IAllFgModule {

    /**
     * 获取所有的测试
     * @return
     */
    List<ITaskRoot> getTaskList();

}
