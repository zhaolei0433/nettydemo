package com.example.mongdbdemo;

import com.example.mongdbdemo.dao.UserDao;
import com.example.mongdbdemo.dao.secondDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongdbdemoApplicationTests {


    @Autowired
    UserDao userDao;

    @Autowired
    com.example.mongdbdemo.dao.secondDao secondDao;

    @Test
    public void contextLoads() {
        System.out.println(userDao.findAll());
    }

}

