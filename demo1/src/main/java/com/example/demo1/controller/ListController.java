package com.example.demo1.controller;


import com.example.demo1.model.News;
import com.example.demo1.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @auther willi
 * @create-time 2019-03-24-14:31
 */

@Controller
public class ListController {
    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping("/list")
    public String list(Model model){
        List<News> newsList = newsRepository.findAll();
        model.addAttribute("news",newsList);
        return "list";
    }

}
