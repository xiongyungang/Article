package com.xyg.service;

import com.xyg.domain.Article;
import com.xyg.domain.Comment;

import java.util.List;

public interface CommentService {
    public void saveComment(Comment comment);
    public List<Comment> getComments(Article article);
}
