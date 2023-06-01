package com.yehwang.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
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
@Data
@Accessors(chain = true)
@TableName("personal_reports")
public class PersonalReportsEntity {

    @TableField("per_id")
    private String perId;

    @TableId(value = "rep_id", type = IdType.AUTO)
    private Integer repId;

    @TableField("rep_username")
    private String repUsername;

    @TableField("rep_task_finished")
    private Integer repTaskFinished;

    @TableField("rep_task_unfinished")
    private Integer repTaskUnfinished;

    @TableField("rep_income")
    private Float repIncome;

    @TableField("rep_outlay")
    private Float repOutlay;

    @TableField("rep_begin")
    private String repBegin;

    @TableField("rep_end")
    private String repEnd;

}
