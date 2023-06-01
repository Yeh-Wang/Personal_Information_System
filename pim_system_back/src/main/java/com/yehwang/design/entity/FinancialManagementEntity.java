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
 * @since 2022-11-21 14:41:01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("financial_management")
public class FinancialManagementEntity {

    @TableField("per_id")
    private String perId;

    @TableId(value = "fin_id", type = IdType.AUTO)
    private Integer finId;

    @TableField("fin_user_name")
    private String finUserName;

    @TableField("fin_income")
    private Float finIncome;

    @TableField("fin_outlay")
    private Float finOutlay;

    @TableField("fin_program")
    private String finProgram;

    @TableField("fin_time")
    private String finTime;

    @TableField("fin_amount")
    private Float finAmount;

    @TableField("fin_add_time")
    private String finAddTime;


}
