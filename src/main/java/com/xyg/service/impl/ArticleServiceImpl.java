package com.xyg.service.impl;

import com.xyg.domain.Article;
import com.xyg.domain.Category;
import com.xyg.domain.User;
import com.xyg.repository.ArticleRepository;
import com.xyg.repository.CategoryRepository;
import com.xyg.service.ArticleService;
import com.xyg.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private Integer PAGE_SIZE = 5;//每页显示记录数

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void deleteArticleById(Integer id) {
        articleRepository.delete(id);
    }

    @Override
    public Article findArticleByAjax() {
        List<Article> articles = articleRepository.getArticlesByUserId();
        int len = articles.size();
        int i = new Random().nextInt(len);
        return articles.get(i);
    }

    @Override
    public List<Article> findArticleAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findArticleById(Integer articleId) {
        return articleRepository.findOne(articleId);
    }

    @Override
    public List<Article> findArticleByCategory(String name) {
        return articleRepository.getArticleByCategory(name);
    }

    @Override
    public List<Article> findArticleByUser(User user) {
        return articleRepository.findArticleByUserOrderByCreateTimeDesc(user);
    }

    @Override
    public Page<Article> pageCategorySort(String tag, Integer start) {
        Page<Article> articles = null;
        start = start<0?0:start;
        Integer size = PAGE_SIZE;
        //根据分类名称获取分类
        Category category = categoryRepository.findCategoryByCategoryName(tag);

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "articleId");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(start, size, sort);

        //如果分类为null返回所有文章
        if (category == null) {
            return articleRepository.findAll(pageable);
        }

        //筛选分类的文章
        Specification<Article> specification = new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path path = root.get("category");
                return criteriaBuilder.equal(path, category);
            }
        };
        return articleRepository.findAll(specification,pageable);
    }

    @Override
    public List<Article> findArticleByTime(User user, Date start,Date end) {
        return articleRepository.findArticleByUserAndCreateTimeBetweenOrderByCreateTimeDesc(user,start,end);
    }

    @Override
    public Page<Article> pageUserSort(User user,Integer start) {
        Page<Article> articles = null;
        start = start<0?0:start;
        Integer size = PAGE_SIZE;

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "articleId");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(start, size, sort);

        Specification<Article> specification = new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path path = root.get("user");
                return criteriaBuilder.equal(path, user);
            }
        };
        return articleRepository.findAll(specification,pageable);
    }
}
