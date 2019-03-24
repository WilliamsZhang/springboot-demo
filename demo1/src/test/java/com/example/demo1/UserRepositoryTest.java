package com.example.demo1;

import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import com.example.demo1.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.Optional;

/**
 * @auther willi
 * @create-time 2019-03-17-22:49
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void findByUserNameTest() {
        User user = userRepository.findByUserName("williams");
        System.out.println(user.getEmail());

        User user1 = userRepository.findByUserNameAndEmail("williams","williams_z@yeah.net");
        System.out.println(user1.getPassword());

        User user2 = userRepository.findByEmail("williams_z@yeah.net");
        System.out.println(user2.getId());

        User user3 = new User(3,"jarvis","非会员","12345678","williams_z@162.com",new Date(),"离线");
        userRepository.save(user3);

        User user4 = new User(4,"forest","非会员","12345678","williams_z@161.com",new Date(),"离线");
        userRepository.save(user4);

        User user5 = new User(5,"weiwei","非会员","12345678","williams@qq.com",new Date(),"离线");
        userRepository.save(user5);

        int page = 0;
        int size = 4;
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<User> users = userRepository.findAll(pageable);
        System.out.println(users.getTotalElements());
        for(User u : users){
            System.out.println(u.toString());
            System.out.println(u.getUserName());
        }

        User user6 = userRepository.findByUserNameAndPassword("williams","12345678");
        System.out.println(user6.getEmail());

        User user7 = userRepository.findById(2);
        System.out.println(user7.getUserName());

        userRepository.deleteById(5);
    }


    @Test
    public void mailTest(){
        mailService.sendSimpleMail("1175137542@qq.com","这是一封简单邮件","Hello World");

    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("1175137542@qq.com","test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="C:\\Users\\willi\\Desktop\\markdown文件\\springboot\\controller层的多种写法.md";
        mailService.sendAttachmentsMail("1175137542@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\'></img>" +
                "<img src=\'cid:" + rscId + "\'></img></body></html>";
        String imgPath = "C:\\Users\\willi\\Desktop\\2.jpg";

        mailService.sendInlineResourceMail("1175137542@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("1175137542@qq.com","主题：这是模板邮件",emailContent);
    }





}
