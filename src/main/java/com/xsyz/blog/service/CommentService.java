package com.xsyz.blog.service;

import com.xsyz.blog.po.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {
    //查询评论列表
    List<Comment> listCommentByBlogId(Long blogId);

    //保存评论
    int saveComment(Comment comment);
    //删除评论
    void deleteComment(Long id);
}
