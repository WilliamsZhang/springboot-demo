package com.example.demo2.controller;

import com.example.demo2.model.User;
import com.example.demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Size;

/**
 * 后台用户信息分页显示
 *
 * @auther willi
 * @create-time 2019-03-18-15:49
 */

@Controller
public class ListController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/list")
    @Cacheable(value = "user_list")
    public String list(Model model, @RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "size",defaultValue = "6") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<User> users = userRepository.findAll(pageable);
        model.addAttribute("users",users);
        return "user/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        userRepository.deleteById(id);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(){
        return "/user/userAdd";
    }

}
