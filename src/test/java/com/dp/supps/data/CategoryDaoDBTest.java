package com.dp.supps.data;

import com.dp.supps.entities.Category;
import com.dp.supps.entities.Product;
import com.dp.supps.entities.Review;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDaoDBTest {
    
    @Autowired
    ReviewDaoDB reviewDao;

    @Autowired
    ProductDaoDB productDao;

    @Autowired
    CategoryDao categoryDao;

    public CategoryDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Product> products = productDao.getAllProducts();

        for (Product p : products) {
            List<Review> reviews = reviewDao
                    .getReviewsByProductId(p.getProductId());

            for (Review r : reviews) {
                reviewDao.deleteReviewById(r.getId());
            }
        }

        for (Product p : products) {
            productDao.deleteProductById(p.getProductId());
        }
        List<Category> categories = categoryDao.getAllCategories();

        for (Category c : categories) {
            categoryDao.deleteCategoryById(c.getId());
        }
    }

    @Test
    public void testGetCategoryById() {
        Category c = new Category();
        c.setName("Test Category");
        categoryDao.addCategory(c);
        
        Category c2 = new Category();
        c2.setName("Test Category 2");
        categoryDao.addCategory(c2);
        
        Category fromDao = categoryDao.getCategoryById(c2.getId());
        
        assertEquals(fromDao, c2);
    }

    @Test
    public void testAddAndGetAllCategories() {
        Category c = new Category();
        c.setName("Test Category");
        categoryDao.addCategory(c);
        
        Category c2 = new Category();
        c2.setName("Test Category 2");
        categoryDao.addCategory(c2);
        
        List<Category> categories = categoryDao.getAllCategories();
        
        assertEquals(2, categories.size());
    }

    @Test
    public void testDeleteCategoryById() {
        Category c = new Category();
        c.setName("Test Category");
        categoryDao.addCategory(c);
        
        Category c2 = new Category();
        c2.setName("Test Category 2");
        categoryDao.addCategory(c2);
        
        List<Category> categories = categoryDao.getAllCategories();
        
        assertEquals(2, categories.size());
        
        categoryDao.deleteCategoryById(c2.getId());
        
        List<Category> categoriesAfterDelete = categoryDao.getAllCategories();
        
        assertEquals(1, categoriesAfterDelete.size());
    }
}