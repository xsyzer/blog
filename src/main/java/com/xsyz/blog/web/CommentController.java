package com.xsyz.blog.web;

import com.xsyz.blog.po.Blog;
import com.xsyz.blog.po.Comment;
import com.xsyz.blog.po.User;
import com.xsyz.blog.service.BlogServiceImp;
import com.xsyz.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xsyz
 * @created 2020-11-23   21:04
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogServiceImp blogServiceImp;

    @Value("${comment.avatar}")
    private String avatar;
    @GetMapping("/comments/{blogId}")
    public String comment(@PathVariable Long blogId, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session,Model model) {
        comment.setBlogId(comment.getBlog().getId());
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if(user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        if(comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    //    删除评论
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id, RedirectAttributes attributes, Model model){
        commentService.deleteComment(id);
        Blog blog = blogServiceImp.selectAndconvert(blogId);
        model.addAttribute("blog",blog);
        attributes.addAttribute("id",blogId);
        return "redirect:/blog/{id}";
    }

}
