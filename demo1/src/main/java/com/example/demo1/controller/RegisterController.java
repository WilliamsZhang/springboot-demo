package com.example.demo1.controller;

import com.example.demo1.Param.RegisterParam;
import com.example.demo1.common.Result;
import com.example.demo1.common.ResultGenerator;
import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import com.example.demo1.service.MailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 注册
 *
 * @auther willi
 * @create-time 2019-03-23-17:25
 */

@RestController
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@Valid @RequestBody RegisterParam registerParam, BindingResult bindingResult){
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

        if(userRepository.findByUserName(registerParam.getUserName()) != null){
            result = ResultGenerator.getFailResult("用户名已经注册，请更换用户名");
            return result;
        }

        if(userRepository.findByEmail(registerParam.getEmail()) != null){
            result = ResultGenerator.getFailResult("邮箱已经注册，请更换新邮箱");
            return result;
        }

        else{
            User user = new User();
            BeanUtils.copyProperties(registerParam,user);
            user.setRegTime(new Date());
            user.setState("邮箱未验证");
            user.setUserType("非会员");
            userRepository.save(user);
            sendRegisterMail(user);
            result = ResultGenerator.getSucessResult("注册成功");
            return result;
        }
    }



    public void sendRegisterMail(User user){
        Context context = new Context();
        context.setVariable("id",user.getId());
        String emailContent = templateEngine.process("emailTemplate",context);
        mailService.sendTemplateMail(user.getEmail(),"注册邮件",emailContent);
    }
}
