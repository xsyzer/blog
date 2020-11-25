package com.xsyz.blog;

import com.xsyz.blog.mapper.Blogmapper;
import com.xsyz.blog.mapper.Typemapper;
import com.xsyz.blog.mapper.Usermapper;
import com.xsyz.blog.po.Blog;
import com.xsyz.blog.po.Type;
import com.xsyz.blog.po.User;
import com.xsyz.blog.service.BlogServiceImp;
import com.xsyz.blog.service.TypeServiceImp;
import com.xsyz.blog.service.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    TypeServiceImp typeServiceImp;
    @Autowired
    Blogmapper blogmapper;
    @Autowired
    BlogServiceImp blogServiceImp;
    @Test
    void contextLoads() {
        List<Blog> allByUserId = blogServiceImp.findAllByUserId(1l);
        List<Blog> collect = allByUserId.stream()
                .sorted(Comparator.comparing(Blog::getUpdateTime)
                .reversed())
                .limit(2)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
    @Test
    void testUser(){
        List<Type> types = typeServiceImp.selectAll();
        types.forEach(System.out::println);
    }
}

