package com.xsyz.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 返回首页
 *
 * @author xsyz
 * @created 2020-10-13   18:32
 */
@Controller
public class IndexController {
    @RequestMapping("/{id}/{name}")
    public String index(@PathVariable Integer id,@PathVariable String name){
        System.out.println("-------index-------------");
        return "index";
    }
}
