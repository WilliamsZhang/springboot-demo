package com.example.demo2.Param;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @auther willi
 * @create-time 2019-03-18-16:06
 */


public class UserParam {
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 5,max = 15,message = "密码长度要大于5，小于15")
    private String password;

    @Min(value = 0,message = "年龄不能小于0岁")
    @Max(value = 100,message = "年龄不能大于100岁")
    private int age;

    @Email(message = "邮箱格式不正确")
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
