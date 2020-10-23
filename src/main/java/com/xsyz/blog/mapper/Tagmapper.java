package com.xsyz.blog.mapper;

import com.xsyz.blog.po.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Tagmapper {
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into t_tag(name) values(#{name})")
    int saveTag(Tag tag);

    @Select("select * from t_tag")
    List<Tag> selectAll();

    @Delete("delete from t_tag where id=#{id}")
    int delete(Long id);

    @Update("update t_tag set name=#{name} where id=#{id}")
    int update(Tag tag);

    @Select("select * from t_tag where name=#{name}")
    Tag selectByName(String name);

    @Select("select * from t_tag where id=#{id}")
    Tag selectById(Long id);



}
