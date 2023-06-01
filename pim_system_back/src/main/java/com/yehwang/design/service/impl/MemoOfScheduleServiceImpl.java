package com.yehwang.design.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yehwang.design.entity.MemoOfScheduleEntity;
import com.yehwang.design.mapper.MemoOfScheduleMapper;
import com.yehwang.design.service.MemoOfScheduleService;
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
public class MemoOfScheduleServiceImpl extends ServiceImpl<MemoOfScheduleMapper, MemoOfScheduleEntity> implements MemoOfScheduleService {

    private MemoOfScheduleMapper memoOfScheduleMapper;

    @Autowired
    public void setMemoOfScheduleMapper(MemoOfScheduleMapper memoOfScheduleMapper){
        this.memoOfScheduleMapper=memoOfScheduleMapper;
    }

    @Override
    public List<MemoOfScheduleEntity> getAllMemo(){
        MemoOfScheduleEntity memoOfScheduleEntity = new MemoOfScheduleEntity();
        return memoOfScheduleMapper.selectList(new QueryWrapper<>(memoOfScheduleEntity).orderByAsc("memo_id"));
    }

    @Override
    public List<MemoOfScheduleEntity> getAllMemoByDate(String date) {
        return memoOfScheduleMapper.getAllMemoByDate(date);
    }

    @Override
    public int addMemoInfo(MemoOfScheduleEntity memoOfScheduleEntity) {
        return memoOfScheduleMapper.insert(memoOfScheduleEntity);
    }

    @Override
    public int updateMemoInfo(MemoOfScheduleEntity memoOfScheduleEntity) {
        return memoOfScheduleMapper.updateById(memoOfScheduleEntity);
    }

    @Override
    public int deleteMemoInfo(int id) {
        return memoOfScheduleMapper.deleteById(id);
    }
}
