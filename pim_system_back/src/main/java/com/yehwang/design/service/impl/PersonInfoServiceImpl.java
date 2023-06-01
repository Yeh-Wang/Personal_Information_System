package com.yehwang.design.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yehwang.design.entity.PersonInfoEntity;
import com.yehwang.design.mapper.PersonInfoMapper;
import com.yehwang.design.service.PersonInfoService;
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
 * @since 2022-11-21 14:30:18
 */
@Service
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoMapper, PersonInfoEntity> implements PersonInfoService {

    private PersonInfoMapper personInfoMapper;

    @Autowired
    public void setPersonInfoMapper(PersonInfoMapper personInfoMapper){
        this.personInfoMapper=personInfoMapper;
    }

    @Override
    public List<PersonInfoEntity> getAllPersonInfo(){
        return personInfoMapper.selectList(null);
    }

    @Override
    public PersonInfoEntity getUserInfo(){
        return personInfoMapper.selectByMyId("377f481899e7443a80f6c001e8691cbf");
    }

    @Override
    public List<PersonInfoEntity> QueryPersonByName(String name){
        PersonInfoEntity personInfoEntity=new PersonInfoEntity();
        return personInfoMapper.selectList(new QueryWrapper<>(personInfoEntity).like("per_name",name));
    }

    @Override
    public void addPersonInfo(PersonInfoEntity personInfoEntity) {
        personInfoMapper.insert(personInfoEntity);
    }

    @Override
    public int deletePersonInfo(String id) {
        PersonInfoEntity personInfoEntity = new PersonInfoEntity();
        personInfoEntity.setPerId(id);
        return personInfoMapper.deleteById(personInfoEntity);
    }

    @Override
    public int updatePersonInfo(PersonInfoEntity personInfoEntity) {
        return personInfoMapper.updateById(personInfoEntity);
    }
}
