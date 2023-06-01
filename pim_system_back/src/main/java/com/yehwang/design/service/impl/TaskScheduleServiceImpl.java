package com.yehwang.design.service.impl;

import com.yehwang.design.entity.TaskScheduleEntity;
import com.yehwang.design.mapper.TaskScheduleMapper;
import com.yehwang.design.service.TaskScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:02
 */
@Service
public class TaskScheduleServiceImpl extends ServiceImpl<TaskScheduleMapper, TaskScheduleEntity> implements TaskScheduleService {

    private TaskScheduleMapper taskScheduleMapper;

    @Autowired
    public void setTaskScheduleMapper(TaskScheduleMapper taskScheduleMapper){
        this.taskScheduleMapper=taskScheduleMapper;
    }

    @Override
    public List<TaskScheduleEntity> getAllTaskInfo() {
        return taskScheduleMapper.selectList(null);
    }

    @Override
    public List<TaskScheduleEntity> getAllTaskByStatus(boolean status) {
        return taskScheduleMapper.getAllTaskByStatus(status);
    }

    @Override
    public List<TaskScheduleEntity> getAllTaskByDateStatus(String date, boolean status) {
        return taskScheduleMapper.getAllTaskByDateStatus(LocalDate.parse(date),status);
    }

    @Override
    public int addTaskInfo(TaskScheduleEntity taskScheduleEntity) {
        return taskScheduleMapper.insert(taskScheduleEntity);
    }

    @Override
    public int updateTaskInfo(TaskScheduleEntity taskScheduleEntity) {
        return taskScheduleMapper.updateById(taskScheduleEntity);
    }

    @Override
    public int deleteTaskInfo(int id) {
        return taskScheduleMapper.deleteById(id);
    }

    @Override
    public int getCountByDateStatus(String beginDate, String endDate, boolean status) {
        return taskScheduleMapper.getCountByDateStatus(beginDate, endDate, status);
    }
}
