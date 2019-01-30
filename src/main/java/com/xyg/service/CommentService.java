package com.xyg.service;

import com.xyg.domain.Article;
import com.xyg.domain.Comment;
import com.xyg.utils.Result;

import java.util.List;

public interface CommentService {
    public void saveComment(Comment comment);
    public List<Comment> getComments(Article article);
    public Result voteChange(Integer commentId);
}
