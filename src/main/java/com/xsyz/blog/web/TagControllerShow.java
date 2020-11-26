package com.xsyz.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsyz.blog.po.Blog;
import com.xsyz.blog.po.Tag;
import com.xsyz.blog.service.BlogServiceImp;
import com.xsyz.blog.service.TagServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xsyz
 * @created 2020-10-28   14:21
 */
@Controller
@CrossOrigin
public class TagControllerShow {
    @Autowired
    private TagServiceImp tagServiceImp;
    @Autowired
    private BlogServiceImp blogServiceImp;
    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(defaultValue = "1",value = "pn") int pn, @PathVariable long id, Model model){
        PageHelper.startPage(pn,8);
        List<Tag> tags = tagServiceImp.selectAll();
        if(id == -1){
        id = tags.get(0).getId();
        }
        List<Blog> blogByTag = blogServiceImp.getBlogByTag(id);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogByTag);
        model.addAttribute("page",blogPageInfo);
        model.addAttribute("tags",tags);
        model.addAttribute("activeTagId",id);
        return "tags";
    }

   // @RequestParam("/tag/{id}")

}
