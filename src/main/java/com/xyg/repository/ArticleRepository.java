package com.xyg.repository;

import com.xyg.domain.Article;
import com.xyg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer>, JpaSpecificationExecutor<Article> {

    /**
     * 根据分类名获取文章列表，按时间逆序
     * @param name 分类名
     * @return
     */
    @Query("SELECT a FROM Category c ,Article a WHERE a.category = c.categoryId and c.categoryName = :name order by a.createTime desc")
    List<Article> getArticleByCategory(@Param("name")String name);

    /**
     * 根据用户获取文章，按创建时间逆序
     * @param user
     * @return
     */
    List<Article> findArticleByUserOrderByCreateTimeDesc(User user);

    /**
     * 根据用户和时间获取文章，按时间逆序
     * @param user
     * @param start
     * @param end
     * @return
     */
    List<Article> findArticleByUserAndCreateTimeBetweenOrderByCreateTimeDesc(User user, Date start,Date end);
}
