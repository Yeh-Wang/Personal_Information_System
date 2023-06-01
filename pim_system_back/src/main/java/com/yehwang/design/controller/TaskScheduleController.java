package com.yehwang.design.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yehwang.design.entity.TaskScheduleEntity;
import com.yehwang.design.mapper.TaskScheduleMapper;
import com.yehwang.design.service.impl.TaskScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:02
 */
@RestController
@RequestMapping("/task-schedule-entity")
public class TaskScheduleController {

    private TaskScheduleMapper taskScheduleMapper;

    @Autowired
    public void setTaskScheduleMapper(TaskScheduleMapper taskScheduleMapper){
        this.taskScheduleMapper=taskScheduleMapper;
    }
    private TaskScheduleServiceImpl taskScheduleService;

    @Autowired
    public void setTaskScheduleService(TaskScheduleServiceImpl taskScheduleService){
        this.taskScheduleService=taskScheduleService;
    }

    /**
     * 获取所有任务信息
     */
    @ResponseBody
    @GetMapping("/getAllTaskInfo")
    public List<TaskScheduleEntity> getAllTaskInfo(){
        return taskScheduleService.getAllTaskInfo();
    }

    /**
     * 获取指定状态的任务信息
     */
    @ResponseBody
    @GetMapping("/getAllTaskByStatus/{status}")
    public List<TaskScheduleEntity> getAllTaskByStatus(@PathVariable boolean status){
        return taskScheduleService.getAllTaskByStatus(status);
    }

    /**
     * 获取指定日期前指定状态的任务信息
     */
    @ResponseBody
    @GetMapping("/getAllTaskByDateStatus/{date},{status}")
    public List<TaskScheduleEntity> getAllTaskByDateStatus(@PathVariable String date,@PathVariable boolean status){
        return taskScheduleService.getAllTaskByDateStatus(date, status);
    }

    /**
     * add a task schedule Info
     */
    @ResponseBody
    @RequestMapping("/addTaskInfo")
    public int addTaskInfo(@RequestBody TaskScheduleEntity taskScheduleEntity){
        taskScheduleEntity.setPerId("377f481899e7443a80f6c001e8691cbf");
        Integer new_id = taskScheduleMapper.selectOne(new QueryWrapper<TaskScheduleEntity>().select("max(task_id) as taskId")).getTaskId()+1;
        taskScheduleEntity.setTaskId(new_id);
        taskScheduleEntity.setTaskComDate("2000-02-01");
        return taskScheduleService.addTaskInfo(taskScheduleEntity);
    }

    /**
     * update a task schedule Info
     */
    @ResponseBody
    @RequestMapping("/updateTaskInfo")
    public int updateTaskInfo(@RequestBody TaskScheduleEntity taskScheduleEntity){

        return taskScheduleService.updateTaskInfo(taskScheduleEntity);
    }

    /**
     * delete a task schedule Info
     */
    @ResponseBody
    @RequestMapping("/deleteTaskInfo/{id}")
    public int deleteTaskInfo(@PathVariable int id){
        return taskScheduleService.deleteTaskInfo(id);
    }
}
