package com.xyg.repository;

import com.xyg.domain.Article;
import com.xyg.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * 根据文章获取所有评论列表
     * @return
     */
    public List<Comment> findCommentsByArticle(Article article);
}
