package com.xsyz.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsyz.blog.po.Blog;
import com.xsyz.blog.po.Type;
import com.xsyz.blog.po.User;
import com.xsyz.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author xsyz
 * @created 2020-10-16   13:22
 */
@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    @Autowired
    private BlogServiceImp blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagServiceImp tagService;

    @RequestMapping
    public String blogList(@RequestParam(defaultValue = "1", value = "pageNum") Integer pn, @RequestParam(defaultValue = "5", value = "pageSize") Integer ps, Model model){
        PageHelper.startPage(pn,ps);
        List<Blog> blogList = blogService.adminBlogInfo();
        List<Type> types = typeService.selectAll();
        model.addAttribute("types",types);
        model.addAttribute("blogList",blogList);
        PageInfo<Blog> PageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageinfo",PageInfo);
        return "admin/blogs";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(defaultValue = "1",value = "pn") Integer pn,  Blog blog, Model model){
        PageHelper.startPage(pn,8);
        List<Blog> blogs = blogService.searchBlog(blog);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        model.addAttribute("blog",blogs);
        model.addAttribute("pageinfo",blogPageInfo);
        return "admin/blogs :: blogList";
    }

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("types",typeService.selectAll());
        model.addAttribute("tags",tagService.selectAllOnly());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    @RequestMapping("/{id}/add")
    public String toupdate(Model model,Blog blog){
        model.addAttribute("types",typeService.selectAll());
        model.addAttribute("tags",tagService.selectAllOnly());
        Blog byId = blogService.findById(blog.getId());
        List<Long> bt = blogService.findBT(blog.getId());
        StringBuffer sb=new StringBuffer();
        for (Long aLong : bt) {
            if(sb.length()==0){
                sb.append(aLong);
            } else {
                sb.append(","+aLong);
            }
        }
        String string = sb.toString();
        byId.setTagIds(string);
        byId.setType(typeService.selectById(byId.getTypeId()));
        model.addAttribute("blog",byId);

        return "admin/blogs-input";
    }

    @RequestMapping("/input")
    public String input(Blog blog, RedirectAttributes attributes, HttpSession httpSession ){
        blog.setUserId(((User)httpSession.getAttribute("user")).getId());
        blog.setTypeId(blog.getType().getId());
        int b=0;
        if(blog.getId()==null){
            int saveblog = blogService.saveblog(blog);
            b=saveblog;
            List<Long> list = tagService.listTags(blog.getTagIds());
            for (Long o : list) {
                blogService.insertbt(blog.getId(),o);
            }
        } else {
            blog.setUpdateTime(new Date());
            b=blogService.updateBlog(blog);
            blogService.deletebt(blog.getId());
            List<Long> longs = tagService.listTags(blog.getTagIds());
            for (Long aLong : longs) {
                blogService.insertbt(blog.getId(),aLong);
            }
        }
        if(b==0){
            attributes.addFlashAttribute("message","失败");
        } else {
            attributes.addFlashAttribute("message","成功");
        }
        return "redirect:/admin/blogs";
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlogTag(id);
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
