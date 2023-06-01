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
@TableName("memo_of_schedule")
public class MemoOfScheduleEntity {

    @TableId(value = "memo_id", type = IdType.AUTO)
    private Integer memoId;

    @TableField("memo_name")
    private String memoName;

    @TableField("memo_txt")
    private String memoTxt;

    @TableField("memo_time")
    private String memoTime;

    @TableField("per_id")
    private String perId;


}
