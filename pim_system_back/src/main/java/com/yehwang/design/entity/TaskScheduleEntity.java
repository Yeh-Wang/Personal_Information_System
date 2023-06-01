package com.yehwang.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("task_schedule")
public class TaskScheduleEntity {

    @TableField("per_id")
    private String perId;

    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    @TableField("task_name")
    private String taskName;

    @TableField("task_txt")
    private String taskTxt;

    @TableField("task_begin")
    private String taskBegin;

    @TableField("task_end")
    private String taskEnd;

    @TableField("task_status")
    private Boolean taskStatus;

    @TableField("task_com_date")
    private String taskComDate;
}
