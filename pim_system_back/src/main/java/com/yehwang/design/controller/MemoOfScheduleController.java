package com.yehwang.design.controller;

import com.yehwang.design.entity.MemoOfScheduleEntity;
import com.yehwang.design.service.impl.MemoOfScheduleServiceImpl;
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
@RequestMapping("/memo-of-schedule-entity")
public class MemoOfScheduleController {

    private MemoOfScheduleServiceImpl memoOfScheduleService;

    @Autowired
    public void setMemoOfScheduleService(MemoOfScheduleServiceImpl memoOfScheduleService){
        this.memoOfScheduleService=memoOfScheduleService;
    }

    @ResponseBody
    @GetMapping("/getAllMemo")
    public List<MemoOfScheduleEntity> getAllMemo(){
        return memoOfScheduleService.getAllMemo();
    }

    @ResponseBody
    @GetMapping("/getAllMemoByDate/{date}")
    public List<MemoOfScheduleEntity> getAllMemoByDate(@PathVariable String date){
        return memoOfScheduleService.getAllMemoByDate(date);
    }

    @ResponseBody
    @RequestMapping("/addMemoInfo")
    public int addMemoInfo(@RequestBody MemoOfScheduleEntity memoOfScheduleEntity){
        memoOfScheduleEntity.setPerId("377f481899e7443a80f6c001e8691cbf");
        return memoOfScheduleService.addMemoInfo(memoOfScheduleEntity);
    }

    @ResponseBody
    @RequestMapping("/deleteMemoInfo/{id}")
    public int deleteMemoInfo(@PathVariable int id){
        return memoOfScheduleService.deleteMemoInfo(id);
    }

    @ResponseBody
    @RequestMapping("/updateMemoInfo")
    public int updateMemoInfo(@RequestBody MemoOfScheduleEntity memoOfScheduleEntity){
        return memoOfScheduleService.updateMemoInfo(memoOfScheduleEntity);
    }
}
