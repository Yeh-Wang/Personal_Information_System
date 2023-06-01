package com.yehwang.design.service.impl;

import com.yehwang.design.entity.FinancialManagementEntity;
import com.yehwang.design.mapper.FinancialManagementMapper;
import com.yehwang.design.service.FinancialManagementService;
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
 * @since 2022-11-21 14:41:01
 */
@Service
public class FinancialManagementServiceImpl extends ServiceImpl<FinancialManagementMapper, FinancialManagementEntity> implements FinancialManagementService {

    private FinancialManagementMapper financialManagementMapper;

    @Autowired
    public void setFinancialManagementMapper(FinancialManagementMapper financialManagementMapper){
        this.financialManagementMapper=financialManagementMapper;
    }

    @Override
    public List<FinancialManagementEntity> getAllFinancialInfo() {
        return financialManagementMapper.selectList(null);
    }

    @Override
    public List<FinancialManagementEntity> getFinancialInfoByTime(String time) {
        return financialManagementMapper.getByDateTime(LocalDate.parse(time));
    }

    @Override
    public int addFinancialInfo(FinancialManagementEntity financialManagementEntity) {
        return financialManagementMapper.insert(financialManagementEntity);
    }

    @Override
    public int updateFinancialInfo(FinancialManagementEntity financialManagementEntity) {
        return financialManagementMapper.updateById(financialManagementEntity);
    }

    @Override
    public int deleteFinancialInfo(Integer id) {
        return financialManagementMapper.deleteById(id);
    }

    @Override
    public float getSumIncomeByDate(String beginDate, String endDate) {
        return financialManagementMapper.getSumIncomeByDate(beginDate, endDate);
    }

    @Override
    public float getSumOutlayByDate(String beginDate, String endDate) {
        return financialManagementMapper.getSumOutlayByDate(beginDate, endDate);
    }
}
