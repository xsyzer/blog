package com.xsyz.blog.service;

import com.xsyz.blog.NotFoundException;
import com.xsyz.blog.mapper.Blogmapper;
import com.xsyz.blog.po.Blog;
import com.xsyz.blog.po.Tag;
import com.xsyz.blog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xsyz
 * @created 2020-10-16   13:29
 */
@Service
public class BlogServiceImp implements BlogService {
    @Autowired
    Blogmapper blogmapper;
    @Autowired
    TagServiceImp tagServiceImp;
    @Override
    public List<Blog> findAllByUserId(Long userId) {
        List<Blog> allByUserId = blogmapper.findAllByUserId(userId);

        return allByUserId;
    }

    @Override
    public Blog findById(Long id) {
        return blogmapper.findById(id);
    }

    @Override
    public int insertBlog(Blog blog) {
        return blogmapper.insertBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogmapper.deleteBlog(id);
    }

    @Override
    public int deleteBlogTag(Long id) {
        return blogmapper.deleteBlogTag(id);
    }

    @Override
    public List<Blog> searchBlog(Blog blog) {
        return blogmapper.searchBlog(blog);
    }

    @Override
    public List<Blog> adminBlogInfo() {
        return blogmapper.adminBlogInfo();
    }

    @Override
    public List<Long> findBT(Long blogsId) {
        return blogmapper.findBT(blogsId);
    }

    @Override
    public int updatebt(Long blogsId, Long tagsId) {
        return blogmapper.updatebt(blogsId,tagsId);
    }

    @Override
    public int deletebt(Long blogsId) {
        return blogmapper.deletebt(blogsId);
    }

    @Override
    public List<Tag> selectbtString(Long blogsId) {
        return blogmapper.selectbtString(blogsId);
    }

    @Override
    public List<Blog> searchBlogByString(String query) {
        List<Blog> blogs = blogmapper.searchBlogByString(query);
        return blogs;

    }

    public int saveblog(Blog blog){
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }
        else {
            blog.setUpdateTime(new Date());
        }
            return blogmapper.insertBlog(blog);
        }
    public int insertbt(Long blogsId,Long tagsId){
    return blogmapper.insertbt(blogsId,tagsId);
    }

    public int updateBlog(Blog blog) {
        return blogmapper.update(blog);
    }

    public Blog selectAndconvert(Long id){
        Blog blog = blogmapper.findById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogmapper.updateview(id);
        List<Long> bt = blogmapper.findBT(id);
        List<Tag> tags = blogmapper.selectbtString(id);
        b.setTags(tags);
        return b;
    }

    public List<Blog> listRecommendBlogTop(int i) {
        List<Blog> allByUserId = blogmapper.findAllByUserId(1l);
        List<Blog> collect = allByUserId.stream()
                .sorted(Comparator.comparing(Blog::getUpdateTime).reversed())
                .limit(i)
                .collect(Collectors.toList());
        return collect;
    }
}

