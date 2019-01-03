package com.xzkydz.function.search.module;

import java.util.ArrayList;
import java.util.List;

public class DataManager implements IDataManager {


    private List<String> unfinishTaskNameLists ; // 未完成测试任务名称


    /**
     * @param taskLists : 全部测试任务集合
     * @return 全部测试任务名称
     */
    @Override
    public List<String> taskNameList(List<ITaskRoot> taskLists) {
        List<String> resultLists = new ArrayList<>();
        unfinishTaskNameLists = new ArrayList<>();

        for (ITaskRoot task : taskLists) {
            if (!resultLists.contains(task.getUnitName())){
                resultLists.add(task.getUnitName());
            }
            if (!unfinishTaskNameLists.contains(task.getUnitName()) && !task.getTaskStatue()){
                unfinishTaskNameLists.add(task.getUnitName());
            }
        }
        return resultLists;
    }


    /**
     * 未完成测试任务的名称集合
     * @return
     */
    @Override
    public List<String> unFinishTaskNameList() {
        return unfinishTaskNameLists;
    }


    /**
     * 最后选中的测试任务
     * @param taskRoot ： 最后选中的测试任务
     * @return
     */
    @Override
    public ITaskRoot getResultTask(ITaskRoot taskRoot) {
        return taskRoot;
    }

    @Override
    public List<ITaskRoot> someUnitNameTask(List<ITaskRoot> taskRootList, String unitName) {
        List<ITaskRoot> iTaskRoots = new ArrayList<>();
        for (ITaskRoot taskRoot: taskRootList) {
            if (taskRoot.getUnitName().equals(unitName)){
                iTaskRoots.add(taskRoot);
            }
        }
        return iTaskRoots;
    }

}
