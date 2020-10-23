package com.xsyz.blog.controller;

import com.xsyz.blog.po.User;
import com.xsyz.blog.service.UserServiceImp;
import com.xsyz.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author xsyz
 * @created 2020-10-15   11:03
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;
    @GetMapping
    public String admin(){
    return "admin/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
                        RedirectAttributes attributes){
        User user = userServiceImp.selectByName(username);
        if(user!=null&& MD5Utils.code(password).equals(user.getPassword())){
            user.setPassword("null");
            session.setAttribute("user",user);
            return "admin/index";
        }
        else {
           attributes.addFlashAttribute("msg","用户名密码错误");
            return "redirect:/admin";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
