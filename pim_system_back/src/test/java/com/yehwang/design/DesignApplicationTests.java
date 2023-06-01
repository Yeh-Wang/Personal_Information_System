package com.yehwang.design;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yehwang.design.entity.*;
import com.yehwang.design.mapper.FinancialManagementMapper;
import com.yehwang.design.mapper.MemoOfScheduleMapper;
import com.yehwang.design.mapper.PersonInfoMapper;
import com.yehwang.design.mapper.TaskScheduleMapper;
import com.yehwang.design.service.impl.PersonalReportsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class DesignApplicationTests {

    @Autowired
    PersonInfoMapper personMapper;

    @Autowired
    MemoOfScheduleMapper memoOfScheduleMapper;

    @Autowired
    TaskScheduleMapper taskScheduleMapper;

    @Autowired
    FinancialManagementMapper financialManagementMapper;

    @Autowired
    PersonalReportsServiceImpl personalReportsService;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelect(){

//        List<MemoOfScheduleEntity> list = memoOfScheduleMapper.getAllMemoByDate(LocalDateTime.parse("2015-05-25 04:40:47"));
//        MemoOfScheduleEntity memoOfScheduleEntity = list.get(0);
//        System.out.println(taskScheduleMapper.selectOne(new QueryWrapper<TaskScheduleEntity>().select("max(task_id) as taskId")).getTaskId());

//        List<FinancialManagementEntity> list = financialManagementMapper.selectList(null);
//        for(int i=0;i< list.size();i++){
//            list.get(i).setFinAddTime(list.get(i).getFinTime());
//            FinancialManagementEntity financialManagementEntity = list.get(i);
//            financialManagementMapper.updateById(financialManagementEntity);
//        }
//        System.out.println(personalReportsService.createReport("2018-03-05 00:00:00","2019-03-05 23:00:00"));
//        System.out.println(taskScheduleMapper.getCountByDateStatus("2018-03-05 00:00:00","2019-03-05 23:00:00",true));
//        System.out.println(taskScheduleMapper.getCountByDateStatus("2018-03-05 00:00:00","2019-03-05 23:00:00",false));
//        System.out.println(financialManagementMapper.getSumIncomeByDate("2011-03-12 00:00:00","2012-01-31 23:00:00"));
//        System.out.println(financialManagementMapper.getSumOutlayByDate("2011-03-12 00:00:00","2012-01-31 23:00:00"));


//        List<PersonInfoEntity> list = personMapper.selectByMyId("10");
//        PersonInfoEntity personInfoEntity = personMapper.selectById(1);
//        System.out.println(personInfoEntity);
//        list.forEach(System.out::println);
//        UUID uuid = UUID.randomUUID();
//        String id = uuid.toString().replace("-","");
//        PersonInfoEntity new_1=new PersonInfoEntity();
//        new_1.setEntity(id,"闵乙洪","man",21, LocalDate.parse("2002-05-25"),"重庆交通大学","重庆","18739417824","1627082750","student","play games");
//        personMapper.insert(new_1);
    }

}
