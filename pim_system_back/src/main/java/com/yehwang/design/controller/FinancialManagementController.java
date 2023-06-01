package com.yehwang.design.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yehwang.design.entity.FinancialManagementEntity;
import com.yehwang.design.mapper.FinancialManagementMapper;
import com.yehwang.design.service.impl.FinancialManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:01
 */
@RestController
@RequestMapping("/financial-management-entity")
public class FinancialManagementController {

    private FinancialManagementServiceImpl financialManagementService;
    private FinancialManagementMapper financialManagementMapper;

    @Autowired
    public void setFinancialManagementMapper(FinancialManagementMapper financialManagementMapper){
        this.financialManagementMapper=financialManagementMapper;
    }

    @Autowired
    public void setFinancialManagementService(FinancialManagementServiceImpl financialManagementService){
        this.financialManagementService=financialManagementService;
    }

    /**
     * 获得所有财务信息
     */
    @ResponseBody
    @GetMapping("/getAllFinancialInfo")
    public List<FinancialManagementEntity> getAllFinancialInfo(){
        return financialManagementService.getAllFinancialInfo();
    }

    /**
     * 获得指定日期之前的所有财务信息
     */
    @ResponseBody
    @GetMapping("/getFinancialInfoByTime/{time}")
    public List<FinancialManagementEntity> getFinancialInfoByTime(@PathVariable String time){
        return financialManagementService.getFinancialInfoByTime(time);
    }

    /**
     * Add
     */
    @ResponseBody
    @RequestMapping("/addFinancial")
    public int addFinancialInfo(@RequestBody FinancialManagementEntity financialManagementEntity){
        financialManagementEntity.setPerId("377f481899e7443a80f6c001e8691cbf");
        Integer new_id = financialManagementMapper.selectOne(new QueryWrapper<FinancialManagementEntity>().select("max(fin_id) as finId")).getFinId()+1;
        financialManagementEntity.setFinId(new_id);
        return financialManagementService.addFinancialInfo(financialManagementEntity);
    }

    /**
     * Update
     */
    @ResponseBody
    @RequestMapping("/updateFinancial")
    public int updateFinancialInfo(@RequestBody FinancialManagementEntity financialManagementEntity){
        return financialManagementService.updateFinancialInfo(financialManagementEntity);
    }

    /**
     * Delete
     */
    @ResponseBody
    @RequestMapping("/deleteFinancial/{id}")
    public int deleteFinancialInfo(@PathVariable Integer id){
        return financialManagementService.deleteFinancialInfo(id);
    }
}
