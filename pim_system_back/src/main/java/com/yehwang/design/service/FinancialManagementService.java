package com.yehwang.design.service;

import com.yehwang.design.entity.FinancialManagementEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:01
 */
public interface FinancialManagementService extends IService<FinancialManagementEntity> {

    /**
     * 获得所有财务信息
     */
    List<FinancialManagementEntity> getAllFinancialInfo();

    /**
     * 获得指定日期之前的所有财务信息
     */
    List<FinancialManagementEntity> getFinancialInfoByTime(String time);

    /**
     * 添加一条财务信息
     */
    int addFinancialInfo(FinancialManagementEntity financialManagementEntity);

    /**
     * 更新一条财务信息
     */
    int updateFinancialInfo(FinancialManagementEntity financialManagementEntity);

    /**
     * 删除一条财务信息
     */
    int deleteFinancialInfo(Integer id);

    /**
     * 获取指定日期范围内总收出
     */
    float getSumIncomeByDate(String beginDate, String endDate);

    /**
     * 获取指定日期范围内总支出
     */
    float getSumOutlayByDate(String beginDate, String endDate);
}
