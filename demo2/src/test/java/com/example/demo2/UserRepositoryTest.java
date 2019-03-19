package com.example.demo2;

import com.example.demo2.model.User;
import com.example.demo2.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @auther willi
 * @create-time 2019-03-17-22:49
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

//    @Rule
//    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void findByUserNameTest(){
//        User user = userRepository.findByUserName("williams");
////        assertThat(this.outputCapture.toString().contains("Williams")).isTrue();
//        System.out.println(user.getEmail());
//
//        User user1 = userRepository.findByUserNameAndEmail("williams","1175137542@qq.com");
//        System.out.println(user1.getPassword());
//
//        User user2 = userRepository.findByEmail("1175137542@qq.com");
//        System.out.println(user2.getId());
//
//        User user3 = new User("3","jarvisforest","非会员","12345678","williams_z@163.com",20,new Date(),"离线");
//        userRepository.save(user3);
//
//        Optional<User> id = userRepository.findById("1");
//        System.out.println(id.isPresent());

//        userRepository.deleteById("3");

//        int page = 0;
//        int size = 1;
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable = PageRequest.of(page,size,sort);
//        Page<User> users = userRepository.findAll(pageable);
////        System.out.println(users.getTotalElements());
//        for(User u : users){
//            System.out.println(u.toString());
//            System.out.println(u.getUserName());
//        }

        User user4 = userRepository.findByUserNameAndPassword("williams","123456");
        System.out.println(user4.getEmail());

        User user = userRepository.findById(2);
        System.out.println(user.getUserName());




    }



}
