package com.example.demo2.repository;

import com.example.demo2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
//    Optional<User> findById(String id);
    User findById(int id);
    User findByUserName(String userName);
    User findByUserNameAndEmail(String userName,String email);
    User findByEmail(String email);
    User findByUserNameAndPassword(String userName,String password);

    @Modifying
    @Transactional
    void deleteById(int id);
    Page<User> findAll(Pageable pageable);

}
