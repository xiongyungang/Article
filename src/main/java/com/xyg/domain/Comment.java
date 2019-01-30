package com.xyg.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    private Integer commentId;
    private Date createTime;
    private String content;
    private Article article;
    private User user;
    private String userName;
    //点赞状态
    private Integer voteStates;

    @Id
    @GeneratedValue
    public Integer getCommentId() {
        return commentId;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    public Article getArticle() {
        return article;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getContent() {
        return content;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getVoteStates() {
        return voteStates;
    }

    public void setVoteStates(Integer voteStates) {
        this.voteStates = voteStates;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", article=" + article +
                ", user=" + user +
                ", userName='" + userName + '\'' +
                ", voteStates=" + voteStates +
                '}';
    }
}
