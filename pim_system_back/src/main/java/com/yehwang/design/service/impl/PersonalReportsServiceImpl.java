package com.yehwang.design.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yehwang.design.entity.PersonalReportsEntity;
import com.yehwang.design.mapper.PersonalReportsMapper;
import com.yehwang.design.service.PersonalReportsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PersonalReportsServiceImpl extends ServiceImpl<PersonalReportsMapper, PersonalReportsEntity> implements PersonalReportsService {

    private PersonalReportsMapper personalReportsMapper;
    private TaskScheduleServiceImpl taskScheduleService;
    private FinancialManagementServiceImpl financialManagementService;

    @Autowired
    public void setFinancialManagementService(FinancialManagementServiceImpl financialManagementService){
        this.financialManagementService=financialManagementService;
    }
    @Autowired
    public  void setTaskScheduleServiceImpl(TaskScheduleServiceImpl taskScheduleServiceImpl){
        this.taskScheduleService = taskScheduleServiceImpl;
    }
    @Autowired
    public void setPersonalReportsMapper(PersonalReportsMapper personalReportsMapper){
        this.personalReportsMapper=personalReportsMapper;
    }

    @Override
    public int createReport(PersonalReportsEntity personalReports) {
        String beginTime = personalReports.getRepBegin();
        String endTime = personalReports.getRepEnd();
        String rep_name = personalReports.getRepUsername();
        PersonalReportsEntity personalReportsEntity = new PersonalReportsEntity();
        int rep_id = personalReportsMapper.selectOne(new QueryWrapper<PersonalReportsEntity>().select("max(rep_id) as repId")).getRepId()+1;
        int task_1 = taskScheduleService.getCountByDateStatus(beginTime,endTime,true);
        int task_0 = taskScheduleService.getCountByDateStatus(beginTime,endTime,false);
        float task_income = financialManagementService.getSumIncomeByDate(beginTime, endTime);
        float task_outlay = financialManagementService.getSumOutlayByDate(beginTime, endTime);
        personalReportsEntity.setPerId("377f481899e7443a80f6c001e8691cbf");
        personalReportsEntity.setRepId(rep_id);
        personalReportsEntity.setRepUsername(rep_name);
        personalReportsEntity.setRepTaskFinished(task_1);
        personalReportsEntity.setRepTaskUnfinished(task_0);
        personalReportsEntity.setRepIncome(task_income);
        personalReportsEntity.setRepOutlay(task_outlay);
        personalReportsEntity.setRepBegin(beginTime);
        personalReportsEntity.setRepEnd(endTime);
        return personalReportsMapper.insert(personalReportsEntity);
    }

    @Override
    public List<PersonalReportsEntity> getAllReports() {
        return personalReportsMapper.selectList(null);
    }

    @Override
    public int deleteReport(int id) {
        return personalReportsMapper.deleteById(id);
    }
}
