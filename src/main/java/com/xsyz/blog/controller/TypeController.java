package com.xsyz.blog.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsyz.blog.po.Type;
import com.xsyz.blog.service.TypeService;
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
@RequestMapping("/admin/types")
public class TypeController {
    @Autowired
    private TypeService typeService;
    //主页面
    @RequestMapping
    public String type(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //传入页码，和每页的页数
       PageHelper.startPage(pn,3);
        List<Type> types = typeService.selectAll();
        model.addAttribute("types",types);
        PageInfo<Type> typePageInfo = new PageInfo<>(types);
        model.addAttribute("info",typePageInfo);
        return "admin/types";
    }
    //跳转到增加页面
    @RequestMapping("/add")
    public String addType(){
        return "admin/types-input";
    }
    //插入新类型
    @RequestMapping("/insert")
    public String insert(Type type, RedirectAttributes attributes){
        Type type1 = typeService.selectByName(type.getName());
        if(type1!=null){
            attributes.addFlashAttribute("msg","已有此类型，勿重复添加");
            return "redirect:/admin/types/add";
        }
        else{
            typeService.saveType(type);
            attributes.addFlashAttribute("msg","添加成功");
            return "redirect:/admin/types/add";
        }
    }
    //删除类型
    @RequestMapping("/delete")
    public String delete( @RequestParam Long id){
        typeService.delete(id);

        return "redirect:/admin/types";
    }
    //更新类型
    @RequestMapping("/update")
    public String update(@RequestParam Long id ,Model model){
        model.addAttribute("id",id);
        return "admin/types-input";
    }
    @RequestMapping("/updates/{id}")
    public String updates(@PathVariable Long id, Type type,Model model){
        Type type1 = typeService.selectByName(type.getName());
        System.out.println(type);
        System.out.println(type.getName());
        System.out.println(type1);
        if(type1!=null){
            model.addAttribute("msg","勿重复添加");
            return "admin/types-input";
        }
        typeService.update(type);
        return "redirect:/admin/types";
    }
}
