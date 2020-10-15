package com.xsyz.blog;

import com.xsyz.blog.mapper.Usermapper;
import com.xsyz.blog.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    Usermapper usermapper;
    @Test
    void contextLoads() {
    }
    @Test
    void testUser(){
        User user = usermapper.selectById(1);
        System.out.println(user);
    }
}
