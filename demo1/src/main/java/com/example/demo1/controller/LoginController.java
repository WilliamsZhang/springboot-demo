package com.example.demo1.controller;

/**
 * 登陆功能
 *
 * @auther willi
 * @create-time 2019-03-18-16:37
 */

import com.example.demo1.Param.LoginParam;
//import com.example.demo1.config.WebConfiguration;
import com.example.demo1.common.Result;
import com.example.demo1.common.ResultGenerator;
import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 登录功能实现
 *
 * @auther willi
 * @create-time 2019-03-18-2:02
 */

@RestController
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login",method = RequestMethod.POST)

    public Result login(@Valid @RequestBody LoginParam loginParam, BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest){
        Result result = null;
        String errorMsg = "";
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error : errors){
                errorMsg = errorMsg + error.getCode() + "-" + error.getDefaultMessage() +";";
            }
            result = ResultGenerator.getFailResult(errorMsg);
            return result;
        }

        User user = userRepository.findByUserName(loginParam.getUserName());
        if(user == null){
            user = userRepository.findByEmail(loginParam.getUserName());
        }
        if(user == null){
            result = ResultGenerator.getFailResult("不存在此用户");
            return result;
        }
        else if(!user.getPassword().equals(loginParam.getPassword())){
            result = ResultGenerator.getFailResult(404,"密码错误");
            return result;
        }
        else {
//            httpServletRequest.getSession().setAttribute(WebConfiguration.LOGIN_KEY,user.getId());
//            httpServletRequest.getSession().setAttribute(WebConfiguration.LOGIN_USER,user);
//            return "redirect:/list";
            result = ResultGenerator.getSucessResult();
            return result;
        }
    }


}
