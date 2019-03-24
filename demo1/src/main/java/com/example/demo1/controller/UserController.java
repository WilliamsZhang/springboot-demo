package com.example.demo1.controller;

import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @auther willi
 * @create-time 2019-03-20-21:32
 */

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/loginPage","/toLogin"},method = RequestMethod.GET)
    public String loginPage(){
//        User user = (User) httpServletRequest.getSession().getAttribute(WebConfiguration.LOGIN_USER);
//        if(user != null){
//            return "user/list";
//        }
        return "login";
    }

    @RequestMapping(value = "/registerPage",method = RequestMethod.GET)
    public String registerPage(){
        return "register";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String mailTemplate(){
        return "index";
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
}
