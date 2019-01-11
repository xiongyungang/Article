package com.xyg.service;

import com.xyg.domain.Category;

import java.util.List;

/**
 * 分类Service
 */
public interface CategoryService {
    /**
     * 获取所有分类
     * @return
     */
    public List<Category> getAll();

    /**
     * 根据名称获取分类
     * @return
     */
    public Category getByName(String name);
}
