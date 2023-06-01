package com.yehwang.design.controller;

import com.yehwang.design.entity.PersonInfoEntity;
import com.yehwang.design.service.impl.PersonInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:30:18
 */
@RestController
@RequestMapping("/person-info-entity")
public class PersonInfoController {
    PersonInfoServiceImpl personInfoService;
    @Autowired
    public void setPersonInfoService(PersonInfoServiceImpl personInfoService){
        this.personInfoService=personInfoService;
    }
    /**
     *  获得所有的通讯录信息，包括用户自己
     */
    @ResponseBody
    @GetMapping("/getAllInfo")
    public List<PersonInfoEntity> getAllPersonInfo(){
        return personInfoService.getAllPersonInfo();
    }
    /**
     *  获得用户个人信息
     */
    @ResponseBody
    @GetMapping("/getSelfInfo")
    public PersonInfoEntity getUserInfo(){
        return personInfoService.getUserInfo();
    }
    /**
     * 通过名字模糊查询
     */
    @ResponseBody
    @GetMapping("/queryPersonByName/{name}")
    public List<PersonInfoEntity> QueryPersonByName(@PathVariable String name){
        return personInfoService.QueryPersonByName(name);
    }
    /**
     * 添加一个用户信息
     */
    @ResponseBody
    @RequestMapping("/addPersonInfo")
    public String addPersonInfo(@RequestBody PersonInfoEntity personInfoEntity){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replace("-","");
        personInfoEntity.setPerId(id);
        personInfoService.addPersonInfo(personInfoEntity);
        return "1";
    }
    /**
     * delete a personInfo
     */
    @ResponseBody
    @RequestMapping("/deletePersonInfo/{id}")
    public int deletePersonInfo(@PathVariable String id){
        return personInfoService.deletePersonInfo(id);
    }
    /**
     * Update a person information
     */
    @ResponseBody
    @RequestMapping("/updatePersonInfo")
    public int updatePersonInfo(@RequestBody PersonInfoEntity personInfoEntity){
        return personInfoService.updatePersonInfo(personInfoEntity);
    }
}
