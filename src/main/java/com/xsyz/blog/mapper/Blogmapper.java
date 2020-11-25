package com.xsyz.blog.mapper;

import com.xsyz.blog.po.BT;
import com.xsyz.blog.po.Blog;
import com.xsyz.blog.po.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author xsyz
 * @created 2020-10-16   13:30
 */
@Repository
public interface Blogmapper {

    List<Blog> findAllByUserId(Long userId);
    Blog findById(Long id);
    int insertBlog(Blog blog);
    int deleteBlog(Long id);
    int deleteBlogTag(Long id);
    List<Blog> searchBlog(Blog blog);
    List<Blog> adminBlogInfo();
    int insertbt(Long blogsId,Long tagsId);
    List<Long> findBT(Long blogsId);
    int update(Blog blog);
    int updatebt(Long blogsId, Long tagsId);
    int deletebt(Long blogsId);
    int updateview(Long id);
    List<Tag> selectbtString(Long blogsId);
    List<Blog> getBlogByType(Long id);
/* **************************************************************** */
    List<Blog> searchBlogByString(String query);

    List<Blog> getBlogByTag(Long id);
    List<String> getYear();
    List<Blog> findByYear(String year);

}
