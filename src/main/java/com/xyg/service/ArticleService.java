package com.xyg.service;

import com.xyg.domain.Article;
import com.xyg.domain.User;
import org.springframework.data.domain.Page;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;


public interface ArticleService {
    /**
     * 保存文章
     * @param article
     */
    public void saveArticle(Article article);

    /**
     * 更新文章
     * @param article
     */
    public void updateArticle(Article article);
    /**
     * 根据id删除文章
     * @param id
     */
    public void deleteArticleById(Integer id);

    /**
     * 首页下拉获取一篇文章
     * @return
     */
    public Article findArticleByAjax();

    /**
     * 获取所有文章列表
     * @return
     */
    public List<Article> findArticleAll();

    /**
     * 根据id获取一篇文章
     * @return
     */
    public Article findArticleById(Integer articleId);

    /**
     * 根据文章分类查找文章列表
     * @param name
     * @return
     */
    public List<Article> findArticleByCategory(String name);

    /**
     * 根据用户获取文章
     * @param user
     * @return
     */
    public List<Article> findArticleByUser(User user);

    /**
     * 根据分类分页获取文章
     * @param tag   分类名称
     * @param start 开始页数
     * @return
     */
    public Page<Article> pageCategorySort(String tag, Integer start);

    /**
     * 根据用户和时间获取文章
     * @param user
     * @param start
     * @param end
     * @return
     */
    public List<Article> findArticleByTime(User user, Date start, Date end);

    public Page<Article> pageUserSort(User user,Integer start);
}
