package com.yehwang.design.controller;

import com.yehwang.design.entity.PersonalReportsEntity;
import com.yehwang.design.service.impl.PersonalReportsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:02
 */
@RestController
@RequestMapping("/personal-reports-entity")
public class PersonalReportsController {
    private PersonalReportsServiceImpl personalReportsService;

    @Autowired
    public void setPersonalReportsService(PersonalReportsServiceImpl personalReportsService){
        this.personalReportsService=personalReportsService;
    }

    /**
     * 生成一条报表信息并返回
     */
    @ResponseBody
    @RequestMapping("/createReport")
    public int createReport(@RequestBody PersonalReportsEntity personalReportsEntity){
        System.out.println(personalReportsEntity.getRepBegin());
        return personalReportsService.createReport(personalReportsEntity);
    }

    /**
     * 获取所有报表信息
     */
    @ResponseBody
    @GetMapping("/getAllReports")
    public List<PersonalReportsEntity> getAllReports(){
        return personalReportsService.getAllReports();
    }

    /**
     * 删除一条信息
     */
    @ResponseBody
    @RequestMapping("/deleteReport/{id}")
    public int deleteReport(@PathVariable int id){
        return personalReportsService.deleteReport(id);
    }
}
