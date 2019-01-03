package com.xzkydz.greendao.dbUtils;


import com.greendao.manager.TaskEnityDao;
import com.greendao.manager.TaskResEnityDao;
import com.xzkydz.function.gaspublicfunction.MyApp;
import com.xzkydz.greendao.bean.TaskResEnity;


import java.util.List;

public class TaskResUtils {

    /**
     * 添加数据，如果有重复则覆盖
     * @param enity
     */
    public static void insert(TaskResEnity enity) {
        MyApp.getInstance().getDaoInstant().getTaskResEnityDao().insertOrReplace(enity);
    }

    /**
     * 删除数据
     * @param id
     */
    public static void delete(long id) {
        MyApp.getInstance().getDaoInstant().getTaskResEnityDao().deleteByKey(id);
    }

    public static void deleteByTaskId(long id){
       List<TaskResEnity> list = MyApp.getInstance().getDaoInstant().getTaskResEnityDao().queryBuilder().where(TaskResEnityDao.Properties.TaskId.eq(id)).list();
        for (TaskResEnity tes:list) {
            delete(tes.getId());
        }
    }

    /**
     * 更新数据
     *
     * @param enity
     */
    public static void update(TaskResEnity enity) {
        MyApp.getInstance().getDaoInstant().getTaskResEnityDao().update(enity);
    }

    /**
     * 查询Id为1的数据
     * @return
     */
    public static List<TaskResEnity> query(Long id) {
        return MyApp.getInstance().getDaoInstant().getTaskResEnityDao().queryBuilder().where(TaskResEnityDao.Properties.Id.eq(id)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<TaskResEnity> queryAll() {
        return MyApp.getInstance().getDaoInstant().getTaskResEnityDao().loadAll();
    }


}
