package com.yehwang.design.service;

import com.yehwang.design.entity.TaskScheduleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:02
 */
public interface TaskScheduleService extends IService<TaskScheduleEntity> {


    /**
     * 获取指定状态的任务信息
     */
    List<TaskScheduleEntity> getAllTaskByStatus(boolean status);

    /**
     * 获取所有任务信息
     */
    List<TaskScheduleEntity> getAllTaskInfo();

    /**
     * 获取指定日期前指定状态的任务信息
     */
    List<TaskScheduleEntity> getAllTaskByDateStatus(String date,boolean status);

    /**
     * 添加一个任务
     */
    int addTaskInfo(TaskScheduleEntity taskScheduleEntity);

    /**
     * 更新一个任务信息
     */
    int updateTaskInfo(TaskScheduleEntity taskScheduleEntity);

    /**
     * 删除一个任务
     */
    int deleteTaskInfo(int id);

    /**
     * 指定时间范围内指定状态信息条数
     */
    int getCountByDateStatus(String beginDate,String endDate,boolean status);
}
