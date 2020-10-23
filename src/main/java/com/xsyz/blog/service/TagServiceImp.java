package com.xsyz.blog.service;

import com.xsyz.blog.mapper.Tagmapper;
import com.xsyz.blog.po.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xsyz
 * @created 2020-10-18   17:29
 */
@Service
public class TagServiceImp implements TagService {

    @Autowired
    Tagmapper tagmapper;

    @Override
    public int saveTag(Tag tag) {
        return tagmapper.saveTag(tag);
    }

    @Override
    public List<Tag> selectAll() {
        return tagmapper.selectAll();
    }

    @Override
    public int delete(Long id) {
        return tagmapper.delete(id);
    }

    @Override
    public int update(Tag tag) {
        return tagmapper.update(tag);
    }

    @Override
    public Tag selectByName(String name) {
        return tagmapper.selectByName(name);
    }

    @Override
    public Tag selectById(Long id) {
        return tagmapper.selectById(id);
    }

    @Override
    public List<Long> listTags(String tagIds) {
        List<Long> tags = Arrays.stream(tagIds.split(","))
                                .map(a->Long.parseLong(a.trim()))
                                .collect(Collectors.toList());
        return tags;
    }
}
