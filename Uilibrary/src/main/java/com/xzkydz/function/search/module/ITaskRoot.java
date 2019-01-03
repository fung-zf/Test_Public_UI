package com.xzkydz.function.search.module;


/**
 *  Date: 2018/4/25 下午2:52
 *  Description: 测试任务对象必须实现的接口，主要用于对测试任务的搜索
 */
public interface ITaskRoot {
    Long getTaskId(); //任务ID
    String getUnitName(); // 受检单位名称
    String getTestFunction(); //测试方法
    String getNumber(); // 任务编号
    String getPeopleName(); //测试员名称
    int getTaskHaveGetData(); //已经获得的测试数据条数
    boolean getTaskStatue(); // 测试任务的状态
    String getGreateTaskTime(); //测试人完成创建的时间
}
