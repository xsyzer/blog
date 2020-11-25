package com.xsyz.blog.web;

import com.xsyz.blog.service.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xsyz
 * @created 2020-11-23   16:35
 */
@Controller
public class Archive {
    @Autowired
    private BlogServiceImp blogServiceImp;
    @GetMapping("/archive")
    public String archive(Model model) {
        model.addAttribute("archiveMap",blogServiceImp.archiveBlog());
        model.addAttribute("blogCount",blogServiceImp.countBlog());
        return "archives";
    }

}
