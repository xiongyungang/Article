package com.xyg.repository;

import com.xyg.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void getIdByName() throws Exception {
        Category category= categoryRepository.findCategoryByCategoryName("新闻");
        System.out.println(category.getCategoryId());
    }
    @Test
    public void findCategoryByCategoryId() throws Exception {
        Category category = categoryRepository.findCategoryByCategoryId(1);
        System.out.println(category);
    }

}