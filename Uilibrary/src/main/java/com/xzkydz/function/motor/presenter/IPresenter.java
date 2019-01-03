package com.xzkydz.function.motor.presenter;

public interface IPresenter {

    /*
     * description：新建电机
     */
    void addMotorToDb();

    /*
     * description:搜索电机
     */
    void searchMotor();

    /*
     * description:清除检索条件
     */
    void clearCondition();
}
