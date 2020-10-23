package com.xsyz.blog.service;

import com.xsyz.blog.mapper.Usermapper;
import com.xsyz.blog.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xsyz
 * @created 2020-10-15   10:44
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private Usermapper usermapper;
    @Override
    public User selectById(Integer id) {
        return usermapper.selectById(id);
    }

    @Override
    public User selectByName(String username) {
        return usermapper.selectByName(username);
    }

    @Override
    public int add(User user) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }
}
