package com.xsyz.blog.mapper;

import com.xsyz.blog.po.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Commentmapper {
int saveComment(Comment comment);
List<Comment> findByParentIdNull(@Param("blogId") Long blogId,@Param("ParentId")Long ParentId);
List<Comment> findByParentIdNotNull(@Param("blogId") Long blogId,@Param("ParentId")Long ParentId);
List<Comment> findByReplayId(@Param("blogId") Long blogId,Long childId);
void deleteComment(Long id);


}
