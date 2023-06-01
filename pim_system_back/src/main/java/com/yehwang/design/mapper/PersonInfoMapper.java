package com.yehwang.design.mapper;

import com.yehwang.design.entity.PersonInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:30:18
 */
@Mapper
public interface PersonInfoMapper extends BaseMapper<PersonInfoEntity> {

    /**
     * 根据id查询用户所有信息
     */
//    @Select("select * from pimsystem.person_info ")
    PersonInfoEntity selectByMyId(@Param("id") String id);

    /**
     * 通过名字模糊查询
     */
    @Select("select * from person_info where per_name like #{name}")
    List<PersonInfoEntity> selectByName(@Param("name") String name);
}
