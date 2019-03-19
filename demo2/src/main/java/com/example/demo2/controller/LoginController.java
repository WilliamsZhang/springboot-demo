package com.example.demo2.controller;

/**
 * 登陆功能
 *
 * @auther willi
 * @create-time 2019-03-18-16:37
 */

import com.example.demo2.Param.LoginParam;
import com.example.demo2.config.WebConfiguration;
import com.example.demo2.model.User;
import com.example.demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 登录功能实现
 *
 * @auther willi
 * @create-time 2019-03-18-2:02
 */

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    public String login(@Valid LoginParam loginParam, BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest){
        String errorMsg = "";
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error : errors){
                errorMsg = errorMsg + error.getCode() + "-" + error.getDefaultMessage() +";";
            }
            model.addAttribute("errorMsg",errorMsg);
            return "login";
        }

        User user = userRepository.findByUserName(loginParam.getUserName());
        if(user == null){
            user = userRepository.findByEmail(loginParam.getUserName());

        }
        if(user == null){
            model.addAttribute("errorMsg","不存在此用户");
            return "login";
        }
        else if(!user.getPassword().equals(loginParam.getPassword())){
            model.addAttribute("errorMsg","密码错误");
            return "login";
        }
        else {
            httpServletRequest.getSession().setAttribute(WebConfiguration.LOGIN_KEY,user.getId());
            httpServletRequest.getSession().setAttribute(WebConfiguration.LOGIN_USER,user);
            return "redirect:/list";
        }
    }

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "user/userAdd";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("user");
        return "login";

    }

}
