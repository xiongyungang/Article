package com.xyg.controller;

import com.xyg.domain.User;
import com.xyg.service.FavoriteService;
import com.xyg.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("/favorite/{articleID}")
    @ResponseBody
    public Result isFavorite(Integer articleId,HttpSession session) {
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
}
