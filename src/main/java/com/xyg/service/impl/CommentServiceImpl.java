package com.xyg.service.impl;

import com.xyg.domain.Article;
import com.xyg.domain.Comment;
import com.xyg.repository.CommentRepository;
import com.xyg.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    /**
     * 添加评论
     * @param comment
     */
    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 根据文章获取所有评论
     * @param article
     * @return
     */
    @Override
    public List<Comment> getComments(Article article) {
        return commentRepository.findCommentsByArticle(article);
    }

}
