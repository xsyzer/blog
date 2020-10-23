package com.xsyz.blog.service;

import com.xsyz.blog.po.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {
    int saveType(Type type);
    List<Type> selectAll();
    int delete(Long id);
    int update(Type type);
    Type selectByName(String name);
    Type selectById(Long id);
}
