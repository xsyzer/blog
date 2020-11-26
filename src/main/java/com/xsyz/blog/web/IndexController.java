package com.xsyz.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsyz.blog.po.Blog;
import com.xsyz.blog.po.Tag;
import com.xsyz.blog.po.Type;
import com.xsyz.blog.service.BlogServiceImp;
import com.xsyz.blog.service.TagServiceImp;
import com.xsyz.blog.service.TypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xsyz
 * @created 2020-10-21   22:33
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    BlogServiceImp blogServiceImp;
    @Autowired
    TypeServiceImp typeServiceImp;
    @Autowired
    TagServiceImp tagServiceImp;
    @RequestMapping
    public String index(@RequestParam(defaultValue = "1",value = "pn") Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<Blog> allByUserId = blogServiceImp.findAllByUserId(1L);
        model.addAttribute("blogs",allByUserId);
        List<Type> types = typeServiceImp.selectAll();
        model.addAttribute("types",types);
        List<Tag> tags = tagServiceImp.selectAll();
        model.addAttribute("tags",tags);
        PageInfo<Blog> PageInfo = new PageInfo<>(allByUserId);
        model.addAttribute("pageinfo",PageInfo);
        model.addAttribute("recommendBlogs",blogServiceImp.listRecommendBlogTop(4));
        return "index";
    }
    @GetMapping("/{id}")
    public String getblog(@PathVariable Long id,Model model){
        Blog blog = blogServiceImp.selectAndconvert(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1",value = "pn") Integer pn,
                         @RequestParam String query, Model model) {
            PageHelper.startPage(pn,5);
            List<Blog> blogs = blogServiceImp.searchBlogByString(query);
            model.addAttribute("blogs", blogs);
            PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);

            model.addAttribute("info",blogPageInfo);
            return "search";
        }

}
