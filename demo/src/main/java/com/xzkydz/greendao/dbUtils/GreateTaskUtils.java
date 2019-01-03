package com.xzkydz.greendao.dbUtils;



import com.greendao.manager.TaskEnityDao;
import com.xzkydz.function.gaspublicfunction.MyApp;
import com.xzkydz.greendao.bean.TaskEnity;

import java.util.List;

public class GreateTaskUtils {

    /**
     * 添加数据，如果有重复则覆盖
     * @param greateTask
     */
    public static Long insert(TaskEnity greateTask) {
       return MyApp.getInstance().getDaoInstant().getTaskEnityDao().insert(greateTask);
    }

    /**
     * 删除数据
     * @param id
     */
    public static void delete(long id) {
        MyApp.getInstance().getDaoInstant().getTaskEnityDao().deleteByKey(id);
    }


    public static void delete(TaskEnity taskEnity) {
        MyApp.getInstance().getDaoInstant().getTaskEnityDao().delete(taskEnity);
    }


    /**
     * 更新数据
     *
     * @param greateTaskp
     */
    public static void update(TaskEnity greateTaskp) {
        MyApp.getInstance().getDaoInstant().getTaskEnityDao().update(greateTaskp);
    }

    /**
     * 查询Id为1的数据
     * @return
     */
    public static TaskEnity query(Long id) {
        return MyApp.getInstance().getDaoInstant().getTaskEnityDao().queryBuilder().where(TaskEnityDao.Properties.Id.eq(id)).list().get(0);
    }

    /**
     * 查询全部数据
     */
    public static List<TaskEnity> queryAll() {
        return MyApp.getInstance().getDaoInstant().getTaskEnityDao().loadAll();
    }


}
