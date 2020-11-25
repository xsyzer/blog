package com.xsyz.blog.service;

import com.xsyz.blog.mapper.Blogmapper;
import com.xsyz.blog.mapper.Commentmapper;
import com.xsyz.blog.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xsyz
 * @created 2020-11-24   12:43
 */
@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private Commentmapper commentmapper;
    @Autowired
    private Blogmapper blogmapper;
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> comments = commentmapper.findByParentIdNull(blogId,null);
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentNickname = comment.getNickname();
            //通过blogid和parentid查询相应的子评论
            List<Comment> childComments = commentmapper.findByParentIdNotNull(blogId,id);
            combineChildren(blogId,childComments, parentNickname);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }
    private void combineChildren(Long blogId,List<Comment> childComments, String parentNickname1) {
        //判断是否有一级子回复
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询二级以及所有子集回复
                recursively(blogId,childId, parentNickname);
            }
        }
    }
    private void recursively(Long blogId,Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentmapper.findByReplayId(blogId,childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                //循环迭代找出子集回复
                recursively(blogId,replayId,parentNickname);
            }
        }
    }
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        return commentmapper.saveComment(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentmapper.deleteComment(id);
    }


}
