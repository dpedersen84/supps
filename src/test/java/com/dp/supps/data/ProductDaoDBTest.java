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
public class ProductDaoDBTest {

    @Autowired
    ProductDaoDB productDao;

    @Autowired
    GoalDaoDB goalDao;

    @Autowired
    CategoryDaoDB categoryDao;

    @Autowired
    ReviewDaoDB reviewDao;

    public ProductDaoDBTest() {
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
    public void testCreateAndGetAllProducts() {
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

        productDao.createProduct(p);

        List<Product> products = productDao.getAllProducts();

        assertTrue(products.size() == 1);
    }

    @Test
    public void testGetProductsByGoalId() {
        Goal g = new Goal();
        g.setName("test goal");
        Goal goalOne = goalDao.addGoal(g);

        Goal g2 = new Goal();
        g2.setName("test goal 2");
        Goal goalTwo = goalDao.addGoal(g2);

        Category c = new Category();
        c.setName("test cat");
        categoryDao.addCategory(c);

        Product p = new Product();
        p.setName("test product");
        p.setGoal(goalOne);
        p.setCategory(c);
        p.setInventory(10);
        p.setPrice(new BigDecimal("10"));

        productDao.createProduct(p);

        List<Product> productsFromGoalOne = productDao.getProductsByGoalId(goalOne.getId());
        List<Product> productsFromGoalTwo = productDao.getProductsByGoalId(goalTwo.getId());

        assertTrue(productsFromGoalOne.size() == 1);

        assertTrue(productsFromGoalTwo.size() < 1);
    }

    @Test
    public void testGetProductsByCategoryId() {
        Goal g = new Goal();
        g.setName("test goal");
        Goal goalOne = goalDao.addGoal(g);

        Category c = new Category();
        c.setName("test cat");
        Category catOne = categoryDao.addCategory(c);

        Category c2 = new Category();
        c2.setName("test cat 2");
        Category catTwo = categoryDao.addCategory(c2);

        Product p = new Product();
        p.setName("test product");
        p.setGoal(goalOne);
        p.setCategory(catTwo);
        p.setInventory(10);
        p.setPrice(new BigDecimal("10"));

        productDao.createProduct(p);

        List<Product> productsFromCatOne = productDao.getProductsByCategoryId(catOne.getId());
        List<Product> productsFromCatTwo = productDao.getProductsByCategoryId(catTwo.getId());

        assertTrue(productsFromCatTwo.size() == 1);

        assertTrue(productsFromCatOne.size() < 1);
    }

    @Test
    public void testGetProductById() {
        Goal g = new Goal();
        g.setName("test goal");
        Goal goalOne = goalDao.addGoal(g);

        Category c = new Category();
        c.setName("test cat");
        Category catOne = categoryDao.addCategory(c);

        Product p = new Product();
        p.setName("test product");
        p.setGoal(goalOne);
        p.setCategory(catOne);
        p.setInventory(10);
        p.setPrice(new BigDecimal("10"));

        Product testProduct = productDao.createProduct(p);

        Product fromDao = productDao.getProductById(testProduct.getProductId());

        assertEquals(testProduct.getProductId(), fromDao.getProductId());
    }

    @Test
    public void testUpdateProduct() {
        Goal g = new Goal();
        g.setName("test goal");
        Goal goalOne = goalDao.addGoal(g);

        Category c = new Category();
        c.setName("test cat");
        Category catOne = categoryDao.addCategory(c);

        Product p = new Product();
        p.setName("test product");
        p.setGoal(goalOne);
        p.setCategory(catOne);
        p.setInventory(10);
        p.setPrice(new BigDecimal("10"));

        p = productDao.createProduct(p);

        Product fromDao = productDao.getProductById(p.getProductId());

        assertEquals(p.getName(), fromDao.getName());

        p.setName("updated name");
        productDao.updateProduct(p);

        assertNotEquals(p.getName(), fromDao.getName());

        fromDao = productDao.getProductById(p.getProductId());

        assertEquals(p.getName(), fromDao.getName());
    }

    @Test
    public void testDeleteProductById() {
        Goal g = new Goal();
        g.setName("test goal");
        Goal goalOne = goalDao.addGoal(g);

        Category c = new Category();
        c.setName("test cat");
        Category catOne = categoryDao.addCategory(c);

        Product p = new Product();
        p.setName("test product");
        p.setGoal(goalOne);
        p.setCategory(catOne);
        p.setInventory(10);
        p.setPrice(new BigDecimal("10"));

        p = productDao.createProduct(p);

        Product p2 = new Product();
        p2.setName("test product");
        p2.setGoal(goalOne);
        p2.setCategory(catOne);
        p2.setInventory(10);
        p2.setPrice(new BigDecimal("10"));

        p2 = productDao.createProduct(p);

        List<Product> products = productDao.getAllProducts();

        assertEquals(2, products.size());

        productDao.deleteProductById(p2.getProductId());

        products = productDao.getAllProducts();

        assertEquals(1, products.size());
    }
}
