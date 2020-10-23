package com.xsyz.blog.mapper;

import com.xsyz.blog.po.User;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;



//@Mapper
@Repository
public interface Usermapper {
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Select("Select * from t_user where id=#{id}")
    User selectById(Integer id);
    @Select("Select * from t_user where username=#{username}")
    User selectByName(String username);
    int add(User user);
    int update(User user);
}
