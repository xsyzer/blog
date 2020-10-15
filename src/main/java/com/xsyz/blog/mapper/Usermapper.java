package com.xsyz.blog.mapper;

import com.xsyz.blog.po.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface Usermapper {
    @Select("Select * from t_user where id=#{id}")
    User selectById(Integer id);
    User selectByName(String username);
    int add(User user);
    int update(User user);
}
