package com.xsyz.blog.mapper;

import com.xsyz.blog.po.Type;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Typemapper {
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into t_type(name) values(#{name})")
    int saveType(Type type);
    @Select("select * from t_type")
    List<Type> selectAll();
    int delete(Long id);
    int update(Type type);
    Type selectByName(String name);
    Type selectById(Long id);



}
