package com.example.demo2.controller;

import com.example.demo2.model.User;
import com.example.demo2.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @auther willi
 * @create-time 2019-03-19-16:04
 */

@Controller
public class SendMailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;


    public void sendRegisterMail(User user){
        Context context = new Context();
        context.setVariable("id",user.getId());
        String emailContent = templateEngine.process("emailTemplate",context);
        mailService.sendTemplateMail(user.getEmail(),"注册邮件",emailContent);
    }
}
