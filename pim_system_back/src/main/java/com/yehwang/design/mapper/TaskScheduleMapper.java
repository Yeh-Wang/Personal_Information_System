package com.yehwang.design.mapper;

import com.yehwang.design.entity.TaskScheduleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:02
 */
@Mapper
public interface TaskScheduleMapper extends BaseMapper<TaskScheduleEntity> {

    /**
     * 查询指定状态的任务信息
     */
    @Select("select * from task_schedule where task_status = #{status}")
    List<TaskScheduleEntity> getAllTaskByStatus(@Param("status") boolean status);

    /**
     * 查询指定日期指定状态的任务信息
     */
    @Select("select * from task_schedule where DATE (task_end) >= #{date} and task_status = #{status}")
    List<TaskScheduleEntity> getAllTaskByDateStatus(@Param("date")LocalDate date,@Param("status") boolean status);

    /**
     * 指定时间范围内指定状态信息条数
     */
    @Select("select count(*) from task_schedule where task_com_date >= #{beginDate} and task_com_date <= #{endDate} and task_status = #{status}")
    int getCountByDateStatus(@Param("beginDate") String beginDate,@Param("endDate") String endDate,@Param("status") boolean status);
}
