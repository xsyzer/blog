package com.xsyz.blog.service;

import com.xsyz.blog.po.Tag;
import com.xsyz.blog.po.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    int saveTag(Tag tag);
    List<Tag> selectAll();
    int delete(Long id);
    int update(Tag tag);
    Tag selectByName(String name);
    Tag selectById(Long id);
    List<Long> listTags(String ids);
}
