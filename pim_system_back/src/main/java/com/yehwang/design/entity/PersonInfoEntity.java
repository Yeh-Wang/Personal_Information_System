package com.yehwang.design.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YehWang
 * @since 2022-11-21 14:30:18
 */
@Getter
@Setter
@Data
@Accessors(chain = true)
@TableName("person_info")
public class PersonInfoEntity {

    @TableId(value = "per_id", type = IdType.AUTO)
    private String perId;

    @TableField("per_name")
    private String perName;

    @TableField("per_sexy")
    private String perSexy;

    @TableField("per_age")
    private Integer perAge;

    @TableField("per_birthday")
    private LocalDate perBirthday;

    @TableField("per_school")
    private String perSchool;

    @TableField("per_address")
    private String perAddress;

    @TableField("per_mobile")
    private String perMobile;

    @TableField("per_qq")
    private String perQq;

    @TableField("per_profession")
    private String perProfession;

    @TableField("per_hobbies")
    private String perHobbies;

    public void setEntity(String id,String name,String sexy,int age,LocalDate birthday,String school,String address,String mobile,String qq,String profession,String hobbies){
        this.perId=id;
        this.perName=name;
        this.perSexy=sexy;
        this.perAge=age;
        this.perBirthday=birthday;
        this.perSchool=school;
        this.perAddress=address;
        this.perMobile=mobile;
        this.perQq=qq;
        this.perProfession=profession;
        this.perHobbies=hobbies;
    }
}
