package com.xzkydz.function.gaspublicfunction;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.xzkydz.function.data.view.DataMagerActivity;
import com.xzkydz.function.search.module.ITaskRoot;
import com.xzkydz.function.utils.DateUtil;
import com.xzkydz.greendao.bean.TaskEnity;
import com.xzkydz.greendao.dbUtils.GreateTaskUtils;
import com.xzkydz.greendao.dbUtils.TaskResUtils;

import java.util.ArrayList;
import java.util.List;

public class MyDataManagerActivity extends DataMagerActivity {


    // 获取所有的任务集合
    @Override
    public void getTaskList() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                List<ITaskRoot> list = new ArrayList<>();
                List<TaskEnity> taskEnities = MyApp.getInstance().getDaoInstant().getTaskEnityDao().loadAll();
                list.addAll(taskEnities);
                //注意：最设置数据
                setDataAndPushView(list);
            }
        }).start();
    }

    /**
     *
     * @return 设置数据详情页
     */
    @NonNull
    @Override
    public Class<?> getDataDetailActivity() {
        return MyPdfListActivity.class;
    }

    /**
     * 设置任务为完成状态
     * @param task
     * @return
     */
    @Override
    public boolean setTaskComplete(ITaskRoot task) {
        TaskEnity taskEnity = (TaskEnity)task;
        taskEnity.set_IsCompleteTask(true);
        GreateTaskUtils.update(taskEnity);
        return true;
    }

    /**
     * 删除测试任务
     * @param task
     * @return 成功删除后返回为True
     */
    @Override
    public boolean deleteTaskAndData(ITaskRoot task) {
        GreateTaskUtils.delete(task.getTaskId());
        TaskResUtils.deleteByTaskId(task.getTaskId());
        return true;
    }

}

