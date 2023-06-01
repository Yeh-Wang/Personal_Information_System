package com.yehwang.design.mapper;

import com.yehwang.design.entity.MemoOfScheduleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:41:02
 */
@Mapper
public interface MemoOfScheduleMapper extends BaseMapper<MemoOfScheduleEntity> {

    /**
     * 获得指定日期之前的所有备忘录信息
     */
    @Select("select * from memo_of_schedule where DATE(memo_time) <= #{time}")
    List<MemoOfScheduleEntity> getAllMemoByDate(String time);
}
