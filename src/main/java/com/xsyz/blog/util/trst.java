package com.xsyz.blog.util;

import com.xsyz.blog.po.Type;
import org.junit.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xsyz
 * @created 2020-10-20   17:29
 */
public class trst {
    @Test
    public void test1(){
        ArrayList<Type> types = new ArrayList<>();
        types.add(new Type(1,"a",null));
        types.add(new Type(2,"b",null));
        types.add(new Type(3,"c",null));
        types.add(new Type(4,"d",null));
        ArrayList<Long> list = new ArrayList<>();
        String s=new String();
        s="1,2,3,4";
        List<Long> collect = Arrays.stream(s.split(","))
                .map(a -> Long.parseLong(a.trim()))
                .collect(Collectors.toList());
        System.out.println(collect.getClass());
        collect.forEach(System.out::print);

    }


}
