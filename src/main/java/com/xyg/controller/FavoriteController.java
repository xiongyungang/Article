package com.xyg.controller;

import com.xyg.domain.User;
import com.xyg.service.FavoriteService;
import com.xyg.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 文章收藏controller
 */
@Controller
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    /**
     * 判断当前文章是否被用户收藏
     * @param articleId
     * @param session
     * @return
     */
    @GetMapping("/favorite/{articleId}")
    @ResponseBody
    public Result isFavorite(@PathVariable("articleId")Integer articleId,HttpSession session) {
        User user = (User)session.getAttribute("user");
        return favoriteService.isFavorite(user, articleId);
    }

    /**
     * 添加收藏
     * @param articleId
     * @param session
     * @return
     */
    @PostMapping("/favorite/{articleId}")
    @ResponseBody
    public Result addFavorite(@PathVariable("articleId") Integer articleId, HttpSession session) {
        User user = (User)session.getAttribute("user");
        return favoriteService.addFavorite(user,articleId);
    }

    /**
     * 取消收藏
     * @param articleId
     * @param session
     * @return
     */
    @DeleteMapping("/favorite/{articleId}")
    @ResponseBody
    public Result deleteFavorite(@PathVariable("articleId") Integer articleId, HttpSession session) {
        User user = (User)session.getAttribute("user");
        return favoriteService.deleteFavoriteByProperty(user, articleId);
    }
}
