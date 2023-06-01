package com.yehwang.design.service;

import com.yehwang.design.entity.MemoOfScheduleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:02
 */
public interface MemoOfScheduleService extends IService<MemoOfScheduleEntity> {

    /**
     * 获取所有备忘录信息
     */
    List<MemoOfScheduleEntity> getAllMemo();

    /**
     * 获取指定日期前的备忘录信息
     */
    List<MemoOfScheduleEntity> getAllMemoByDate(String date);

    /**
     * 添加一个备忘录信息
     */
    int addMemoInfo(MemoOfScheduleEntity memoOfScheduleEntity);

    /**
     * 更改备忘录信息
     */
    int updateMemoInfo(MemoOfScheduleEntity memoOfScheduleEntity);

    /**
     * 删除一条备忘录信息
     */
    int deleteMemoInfo(int id);
}
