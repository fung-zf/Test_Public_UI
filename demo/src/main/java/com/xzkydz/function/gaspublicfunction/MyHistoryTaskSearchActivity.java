package com.xzkydz.function.gaspublicfunction;

import com.xzkydz.function.search.module.ITaskRoot;
import com.xzkydz.function.search.view.SearchHistoryTaskActivity;

import java.util.ArrayList;
import java.util.List;


/**
 *  Date: 2018/3/19 9:47
 *  Description: 用于展示产品信息、企业信息
 */
public class MyHistoryTaskSearchActivity extends SearchHistoryTaskActivity {

    @Override
    protected List<ITaskRoot> getTaskDataList() {
        List<ITaskRoot> list = new ArrayList<>();
        Task task0 = new Task("测试","李四","2016.12","15","完成" ,"支管路");
        Task task1 = new Task("进行测试","李四","2016.12","15","完成","支管路");
        Task task6 = new Task("进行测试","李四","2016.12","15","完成","支管路");
        Task task2 = new Task("哈哈","李四","2016.12","15","完成","支管路");
        Task task3 = new Task("aa","李四","2016.12","15","完成","支管路");
        Task task4 = new Task("dd","李四","2016.12","15","完成","支管路");
        Task task5 = new Task("12314","李四","2016.12","15","完成","主管路、泵");
        list.add(task0);
        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);
        list.add(task6);
        return list;
    }

    @Override
    protected Class getTestClass(ITaskRoot resultTask) {
        return MySplash.class;
    }

    // 这里为了示例才这样写,实际是用TaskEntity

    public class Task implements ITaskRoot{

        private String unitName = "测试";
        private String people = "李四";
        private String time = "2016.12.5";
        private String number= "15";
        private String statue = "未完成";
        private String function = "支管路";


        public Task(String unitName, String people, String time, String number, String statue , String function) {
            this.unitName = unitName;
            this.people = people;
            this.time = time;
            this.number = number;
            this.statue = statue;
            this.function = function;
        }

        @Override
        public Long getTaskId() {
            return 12L;
        }

        @Override
        public String getUnitName() {
            return unitName;
        }

        @Override
        public String getTestFunction() {
            return function;
        }

        @Override
        public String getNumber() {
            return number;
        }

        @Override
        public String getPeopleName() {
            return people;
        }

        @Override
        public int getTaskHaveGetData() {
            return 5;
        }

        @Override
        public boolean getTaskStatue() {
            return false;
        }

        @Override
        public String getGreateTaskTime() {
            return time;
        }
    }


}
