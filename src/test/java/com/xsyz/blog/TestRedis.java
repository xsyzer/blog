package com.xsyz.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xsyz
 * @created 2020-10-28   19:59
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        ValueOperations<String, String> opsForValue = this.stringRedisTemplate.opsForValue();
        opsForValue.set("name", "zs"); // 缓存数据
        String value = opsForValue.get("name"); // 获取缓存数据
        System.out.println(value);

    }
}
