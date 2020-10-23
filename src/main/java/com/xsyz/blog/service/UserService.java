package com.xsyz.blog.service;

import com.xsyz.blog.po.User;
import org.springframework.stereotype.Service;

/**
 * @author xsyz
 * @created 2020-10-15   09:11
 */
@Service
public interface UserService {
    User selectById(Integer id);
    User selectByName(String username);
    int add(User user);
    int update(User user);

}
