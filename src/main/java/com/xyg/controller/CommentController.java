package com.xyg.controller;

import com.xyg.domain.Article;
import com.xyg.domain.Comment;
import com.xyg.domain.User;
import com.xyg.service.ArticleService;
import com.xyg.service.CommentService;
import com.xyg.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import java.util.Date;


/**
 * 文章Controller
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @PostMapping(value = "/comment",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result saveComment(Comment comment,Integer articleId, HttpSession session) {
        Article article = articleService.findArticleById(articleId);

        //补全信息
        comment.setCreateTime(new Date());
        User user = (User) session.getAttribute("user");
        comment.setUserName(user.getUserName());
        comment.setUser(user);
        comment.setArticle(article);

        commentService.saveComment(comment);
        return Result.success("ok");
    }

    /**
     * 点赞
     * @param commentId
     * @return
     */
    @GetMapping(value = "/comment/vote/{commentId}")
    @ResponseBody
    public Result changeVote(@PathVariable("commentId") Integer commentId) {
        return commentService.voteChange(commentId);
    }
}
