package com.example.demo1.repository;

import com.example.demo1.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Integer> {

    List<News> findAll();

}
