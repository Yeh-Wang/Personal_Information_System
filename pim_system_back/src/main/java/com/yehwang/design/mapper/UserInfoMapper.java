package com.yehwang.design.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author :Yaxin-Wang
 * @date : 2022/11/26
 */
@Mapper
public interface UserInfoMapper {

    @Select("select 1 from userinfo where username = #{name} limit 1")
    String judgeUsername(@Param("name") String name);

    @Select("select 1 from userinfo where password = #{pwd} limit 1")
    String judgePassword(@Param("pwd") String pwd);
}
