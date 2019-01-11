package com.xyg.repository;

import com.xyg.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    /**
     * 根据名称获取分类
     * @param name
     * @return
     */
    public Category findCategoryByCategoryName(String name);

    /**
     * 根据id获取分类
     * @param categoryId
     * @return
     */
    public Category findCategoryByCategoryId(Integer categoryId);

    /**
     * 查询所有分类
     * @return
     */
    public List<Category> findAll();
}
