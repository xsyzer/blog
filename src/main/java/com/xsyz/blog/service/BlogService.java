package com.xsyz.blog.service;

import com.xsyz.blog.po.Blog;
import com.xsyz.blog.po.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    List<Blog> findAllByUserId(Long userId);
    Blog findById(Long id);
    int insertBlog(Blog blog);
    int deleteBlog(Long id);
    int deleteBlogTag(Long id);
    List<Blog> searchBlog(Blog blog);
    List<Blog> adminBlogInfo();
    List<Long> findBT(Long blogsId);
    int updatebt(Long blogsId, Long tagsId);
    int deletebt(Long blogsId);
    List<Tag> selectbtString(Long blogsId);
    List<Blog> searchBlogByString(String query);


}
