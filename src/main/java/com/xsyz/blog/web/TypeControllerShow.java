package com.xsyz.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsyz.blog.po.Blog;
import com.xsyz.blog.po.Type;
import com.xsyz.blog.service.BlogServiceImp;
import com.xsyz.blog.service.TypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xsyz
 * @created 2020-10-28   14:20
 */
@Controller
@CrossOrigin
public class TypeControllerShow {
    @Autowired
    private TypeServiceImp typeServiceImp;
    @Autowired
    private BlogServiceImp blogServiceImp;
    @GetMapping("/types/{id}")
    public String getType(@RequestParam (defaultValue = "1",value = "pn") int pn, @PathVariable long id, Model model) {
        PageHelper.startPage(pn,8);
        List<Type> types = typeServiceImp.selectAll();
        if(id == -1) {
            id = types.get(0).getId();
        }

        List<Blog> blogByType = blogServiceImp.getBlogByType(id);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogByType);
        model.addAttribute("types",types);
        model.addAttribute("page",blogPageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }

}
