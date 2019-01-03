package com.xzkydz.function.search.module;

import java.util.List;

public interface IDataManager {

    /*
     * description:获取测试任务名称
     */
    List<String> taskNameList (List<ITaskRoot> taskLists);

    /*
     * description:未完成测试的所有测试任务的测试名称
     */
    List<String> unFinishTaskNameList ();
    /*
     * description:获取最终选中的测试任务
     */
    ITaskRoot getResultTask(ITaskRoot taskRoot);

    /**
     * 获取同一单位名称下的所有测试任务
     * @param taskRootList
     * @param unitName
     * @return
     */
    List<ITaskRoot> someUnitNameTask(List<ITaskRoot> taskRootList , String unitName);
}
