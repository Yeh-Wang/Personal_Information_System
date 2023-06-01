package com.yehwang.design.controller;

import com.yehwang.design.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :Yaxin-Wang
 * @date : 2022/11/26
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    UserInfoMapper userInfoMapper;

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper){
        this.userInfoMapper=userInfoMapper;
    }

    @ResponseBody
    @GetMapping("/judgeLogin/{username},{password}")
    public String judgeLogin(@PathVariable String username, @PathVariable String password){
        if(userInfoMapper.judgeUsername(username)!=null){
            if(userInfoMapper.judgePassword(password)!=null){
                return "1";
            }else {
                return "Incorrect password!";
            }
        }else {
            return "Incorrect username!";
        }
    }
}
