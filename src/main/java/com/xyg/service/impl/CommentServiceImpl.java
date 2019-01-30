package com.xyg.service.impl;

import com.xyg.domain.Article;
import com.xyg.domain.Comment;
import com.xyg.repository.CommentRepository;
import com.xyg.service.CommentService;
import com.xyg.utils.Result;
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

    /**
     * 根据评论id进行点赞和取消点赞
     * @param commentId
     */
    @Override
    public Result voteChange(Integer commentId) {
        Comment comment = commentRepository.findCommentsByCommentId(commentId);
        if (comment == null){
            return Result.error("not found comment");
        }

        //1为点赞状态
        if (comment.getVoteStates() == null) {
            comment.setVoteStates(1);
        } else {
            if (comment.getVoteStates() != 1) {
                comment.setVoteStates(1);
            } else {
                comment.setVoteStates(0);
            }
        }

        commentRepository.save(comment);
        return Result.success("ok");
    }

}
