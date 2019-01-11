package com.xyg.service.impl;

import com.xyg.domain.Category;
import com.xyg.repository.CategoryRepository;
import com.xyg.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 文章分类Service
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findCategoryByCategoryName(name);
    }
}
