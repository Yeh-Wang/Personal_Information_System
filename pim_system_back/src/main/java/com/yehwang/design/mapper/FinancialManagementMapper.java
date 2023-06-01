package com.yehwang.design.mapper;

import com.yehwang.design.entity.FinancialManagementEntity;
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
 * @since 2022-11-21 14:41:01
 */
@Mapper
public interface FinancialManagementMapper extends BaseMapper<FinancialManagementEntity> {

    /**
     * 获得指定日期之前的所有财务信息
     */
    @Select("select * from financial_management where Date(fin_add_time) >= #{time}")
    List<FinancialManagementEntity> getByDateTime(LocalDate time);

    /**
     * 获取指定日期范围内总收出
     */
    @Select("select sum(fin_income) from financial_management where fin_time between #{beginDate} and #{endDate}")
    float getSumIncomeByDate(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

    /**
     * 获取指定日期范围内总支出
     */
    @Select("select sum(fin_outlay) from financial_management where fin_time between #{beginDate} and #{endDate}")
    float getSumOutlayByDate(@Param("beginDate") String beginDate, @Param("endDate") String endDate);
}
