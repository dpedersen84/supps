package com.dp.supps.data;

import com.dp.supps.entities.Category;
import com.dp.supps.entities.Goal;
import com.dp.supps.entities.Product;
import com.dp.supps.entities.Review;
import java.math.BigDecimal;
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
public class ReviewDaoDBTest {

    @Autowired
    ReviewDaoDB reviewDao;

    @Autowired
    ProductDaoDB productDao;

    @Autowired
    GoalDaoDB goalDao;

    @Autowired
    CategoryDaoDB categoryDao;

    public ReviewDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Product> products = productDao.getAllProducts();

        if (products != null || products.size() > 0) {
            for (Product p : products) {
                List<Review> reviews = reviewDao
                        .getReviewsByProductId(p.getProductId());

                for (Review r : reviews) {
                    reviewDao.deleteReviewById(r.getId());
                }
            }
        }

        for (Product p : products) {
            productDao.deleteProductById(p.getProductId());
        }

        List<Goal> goals = goalDao.getAllGoals();

        for (Goal g : goals) {
            goalDao.deleteGoalById(g.getId());
        }

        List<Category> categories = categoryDao.getAllCategories();

        for (Category c : categories) {
            categoryDao.deleteCategoryById(c.getId());
        }
    }

    @Test
    public void testGetReviewById() {
        Goal g = new Goal();
        g.setName("test goal");
        goalDao.addGoal(g);

        Category c = new Category();
        c.setName("test cat");
        categoryDao.addCategory(c);

        Product p = new Product();
        p.setName("test product");
        p.setGoal(g);
        p.setCategory(c);
        p.setInventory(10);
        p.setPrice(new BigDecimal("10"));

        Product testProduct = productDao.createProduct(p);

        Review r = new Review();
        r.setProductId(testProduct.getProductId());
        r.setRating(5);
        r.setDescription("Test Description");

        reviewDao.addReview(r);

        Review fromDao = reviewDao.getReviewById(r.getId());

        assertEquals(fromDao, r);
    }

    @Test
    public void testAddAndGetReviewsByProductId() {
        Goal g = new Goal();
        g.setName("test goal");
        goalDao.addGoal(g);

        Category c = new Category();
        c.setName("test cat");
        categoryDao.addCategory(c);

        Product p = new Product();
        p.setName("test product");
        p.setGoal(g);
        p.setCategory(c);
        p.setInventory(10);
        p.setPrice(new BigDecimal("10"));

        Product testProduct = productDao.createProduct(p);

        Review r = new Review();
        r.setProductId(testProduct.getProductId());
        r.setRating(5);
        r.setDescription("Test Description");

        reviewDao.addReview(r);

        Review r2 = new Review();
        r2.setProductId(testProduct.getProductId());
        r2.setRating(5);
        r2.setDescription("Test Description");

        reviewDao.addReview(r2);

        List<Review> reviews = reviewDao.getReviewsByProductId(testProduct.getProductId());

        assertEquals(2, reviews.size());
    }

    @Test
    public void testDeleteReviewById() {
        Goal g = new Goal();
        g.setName("test goal");
        goalDao.addGoal(g);

        Category c = new Category();
        c.setName("test cat");
        categoryDao.addCategory(c);

        Product p = new Product();
        p.setName("test product");
        p.setGoal(g);
        p.setCategory(c);
        p.setInventory(10);
        p.setPrice(new BigDecimal("10"));

        Product testProduct = productDao.createProduct(p);
        
        Review r = new Review();
        r.setProductId(testProduct.getProductId());
        r.setRating(5);
        r.setDescription("Test Description");

        reviewDao.addReview(r);

        Review r2 = new Review();
        r2.setProductId(testProduct.getProductId());
        r2.setRating(5);
        r2.setDescription("Test Description");

        reviewDao.addReview(r2);

        List<Review> reviews = reviewDao.getReviewsByProductId(testProduct.getProductId());

        assertEquals(2, reviews.size());

        reviewDao.deleteReviewById(r2.getId());

        List<Review> reviewsAfterDelete = reviewDao.getReviewsByProductId(testProduct.getProductId());

        assertFalse(reviewsAfterDelete.contains(r2));

        assertEquals(1, reviewsAfterDelete.size());
    }
}
