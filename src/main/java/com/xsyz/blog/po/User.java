package com.xsyz.blog.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    //@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date createTime;
   // @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date updateTime;
    private List<Blog> blogs = new ArrayList<>();

}
