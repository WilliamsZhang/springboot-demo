package com.example.demo2.controller;

import com.example.demo2.Param.UserParam;
import com.example.demo2.model.User;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.service.MailService;
import com.example.demo2.service.impl.MailServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 注册页面
 * @auther willi
 * @create-time 2019-03-18-16:35
 */

@Controller
public class RegisterController {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/userAdd";
    }

    @RequestMapping("/register")
    public String register(Model model, @Valid UserParam userParam, BindingResult bindingResult){
        String errorMsg = "";
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error : errors){
                errorMsg = errorMsg + error.getCode() + "-" + error.getDefaultMessage() +";";
            }
            model.addAttribute("errorMsg",errorMsg);
            return "user/userAdd";
        }

        User user = userRepository.findByUserName(userParam.getUserName());
        if(user != null){
            model.addAttribute("errorMsg","用户名已经存在");
            return "user/userAdd";
        }

        User u = userRepository.findByEmail(userParam.getEmail());
        if(u != null){
            model.addAttribute("errorMsg","邮箱已经存在");
            return "user/userAdd";
        }

        User user1 = new User();
        BeanUtils.copyProperties(userParam,user1);
        user1.setRegTime(new Date());
        user1.setState("邮箱未验证");
        user1.setUserType("非会员");
        userRepository.save(user1);
        sendRegisterMail(user1);
        return "redirect:/list";
    }

    @RequestMapping("verified/{id}")
    public String verified(@PathVariable int id, Model model){
        User user = userRepository.findById(id);
        if(user != null && user.getState().equals("邮箱未验证")){
            user.setState("邮箱已验证");
            userRepository.save(user);
            model.addAttribute("userName",user.getUserName());
        }
        return "verified";
    }


    public void sendRegisterMail(User user){
        Context context = new Context();
        context.setVariable("id",user.getId());
        String emailContent = templateEngine.process("emailTemplate",context);
        mailService.sendTemplateMail(user.getEmail(),"注册邮件",emailContent);
    }
}
