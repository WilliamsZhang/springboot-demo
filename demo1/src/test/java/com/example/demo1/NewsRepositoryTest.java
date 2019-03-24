package com.example.demo1;

import com.example.demo1.model.News;
import com.example.demo1.repository.NewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @auther willi
 * @create-time 2019-03-24-14:39
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepositoryTest {
    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void findAllTest(){
        List<News> news = newsRepository.findAll();
        for(News news1 : news){
            System.out.println(news1.getId());
        }
    }
}
