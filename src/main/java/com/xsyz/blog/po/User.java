package com.xsyz.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xsyz
 * @created 2020-10-14   10:45
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date creatTime;
    private Date updateTime;
    private List<Blog> blogs = new ArrayList<>();

}
