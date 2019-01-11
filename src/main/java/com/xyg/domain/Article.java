package com.xyg.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article")
public class Article implements Serializable{

    //主键
    private Integer articleId;

    private String author;

    private String title;

    private Date createTime;

    private Category category;

    private User user;

    //内容不包含html
    private String content;

    //内容包含html
    private String contentHtml;

    //一篇文章对应多条评论
    private List<Comment> comments = new ArrayList<Comment>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getArticleId() {
        return articleId;
    }

    //多篇文章对应一个分类
    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    //多篇文章对应一个用户
    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "article")
    public List<Comment> getComments() {
        return comments;
    }

    //内容字段为TEXT类型
    @Column(columnDefinition="TEXT")
    public String getContent() {
        return content;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //内容字段为TEXT类型
    @Column(columnDefinition="TEXT")
    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", category=" + category +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", contentHtml='" + contentHtml + '\'' +
                ", comments=" + comments +
                '}';
    }
}
