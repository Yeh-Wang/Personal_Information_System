package com.yehwang.design.service;

import com.yehwang.design.entity.PersonInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:30:18
 */
public interface PersonInfoService extends IService<PersonInfoEntity> {

    /**
     *  获得所有的通讯录信息，包括用户自己
     */
    List<PersonInfoEntity> getAllPersonInfo();

    /**
     *  获得用户个人信息
     */
    PersonInfoEntity getUserInfo();

    /**
     * 通过名字模糊查询
     */
    List<PersonInfoEntity> QueryPersonByName(String name);

    /**
     * 添加一个人物信息
     */
    void addPersonInfo(PersonInfoEntity personInfoEntity);

    /**
     * 删除一个用户信息
     */
    int deletePersonInfo(String id);

    /**
     * Update a person information
     */
    int updatePersonInfo(PersonInfoEntity personInfoEntity);
}
