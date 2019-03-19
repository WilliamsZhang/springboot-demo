package com.example.demo2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Bean
 *
 * @auther willi
 * @create-time 2019-03-17-21:53
 */

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "reg_time", nullable = false)
    private Date regTime;

    @Column(name = "state", nullable = false)
    private String state;

    public User() {
    }

    public User(int id ,String userName, String userType, String password, String email, int age, Date regTime, String state) {
        this.id = id;
        this.userName = userName;
        this.userType = userType;
        this.password = password;
        this.email = email;
        this.age = age;
        this.regTime = regTime;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
