package com.xsyz.blog.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsyz.blog.po.Tag;
import com.xsyz.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author xsyz
 * @created 2020-10-17   11:18
 */
@Controller
@RequestMapping("/admin/tags")
public class TagController {
    @Autowired
    private TagService tagService;
    //主页面
    @RequestMapping
    public String type(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //传入页码，和每页的页数
       PageHelper.startPage(pn,5);
        List<Tag> tags = tagService.selectAllOnly();
        model.addAttribute("tags",tags);
        PageInfo<Tag> tagPageInfo = new PageInfo<>(tags);
        model.addAttribute("info",tagPageInfo);
        return "admin/tags";
    }
    //跳转到增加页面
    @RequestMapping("/add")
    public String addTag(){
        return "admin/tags-input";
    }
    //插入新类型
    @RequestMapping("/insert")
    public String insert(Tag tag, RedirectAttributes attributes){
        Tag tag1 = tagService.selectByName(tag.getName());
        if(tag1!=null){
            attributes.addFlashAttribute("msg","已有此类型，勿重复添加");
            return "redirect:/admin/tags/add";
        }
        else{
            tagService.saveTag(tag);
            attributes.addFlashAttribute("msg","添加成功");
            return "redirect:/admin/tags/add";
        }
    }
    //删除类型
    @RequestMapping("/delete")
    public String delete( @RequestParam Long id){
        tagService.delete(id);

        return "redirect:/admin/tags";
    }
    //更新类型
    @RequestMapping("/update")
    public String update(@RequestParam Long id ,Model model){
        model.addAttribute("id",id);
        return "admin/tags-input";
    }
    @RequestMapping("/updates/{id}")
    public String updates(@PathVariable Long id, Tag tag,Model model){
        Tag tag1 = tagService.selectByName(tag.getName());
        System.out.println(tag1);
        if(tag1!=null){
            model.addAttribute("msg","勿重复添加");
            return "admin/tags-input";
        }
        tagService.update(tag);
        return "redirect:/admin/tags";
    }
}
