package com.xsyz.blog.service;

import com.xsyz.blog.mapper.Typemapper;
import com.xsyz.blog.po.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.TypeElement;
import java.util.List;

/**
 * @author xsyz
 * @created 2020-10-17   11:19
 */
@Service
public class TypeServiceImp implements TypeService {
    @Autowired
    private Typemapper typemapper;
    @Override
    public int saveType(Type type) {
        int i = typemapper.saveType(type);
        return i;
    }

    @Override
    public List<Type> selectAll() {
        return typemapper.selectAll();
    }

    @Override
    public int delete(Long id) {
        return typemapper.delete(id);
    }

    @Override
    public int update(Type type) {
        return typemapper.update(type);
    }

    @Override
    public Type selectByName(String name) {
        return typemapper.selectByName(name);
    }

    @Override
    public Type selectById(Long id) {
        return typemapper.selectById(id);
    }


}
