package com.xsyz.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论
 *
 * @author xsyz
 * @created 2020-10-14   10:40
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Blog blog;
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
    private boolean adminComment;}
