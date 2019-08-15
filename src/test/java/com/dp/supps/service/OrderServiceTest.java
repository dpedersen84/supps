package com.dp.supps.service;

import com.dp.supps.data.CategoryDaoDB;
import com.dp.supps.data.GoalDaoDB;
import com.dp.supps.data.OrderDaoDB;
import com.dp.supps.data.ProductDaoDB;
import com.dp.supps.data.ReviewDaoDB;
import com.dp.supps.data.UserDaoDB;
import com.dp.supps.entities.Category;
import com.dp.supps.entities.Goal;
import com.dp.supps.entities.Order;
import com.dp.supps.entities.Product;
import com.dp.supps.entities.Review;
import com.dp.supps.entities.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;
    
    @Autowired
    OrderDaoDB orderDao;

    @Autowired
    ProductDaoDB productDao;

    @Autowired
    ReviewDaoDB reviewDao;

    @Autowired
    GoalDaoDB goalDao;

    @Autowired
    CategoryDaoDB categoryDao;

    @Autowired
    UserDaoDB userDao;

    public OrderServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Order> orders = orderDao.getAllOrders();

        for (Order o : orders) {
            List<Product> products = o.getProducts();

            for (Product p : products) {
                orderDao.removeFromOrderProduct(o.getOrderId(), p.getProductId());
            }

            orderDao.deleteOrderById(o.getOrderId());
        }

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
    public void testGetUnsentOrderByUserId() {
        User testUser = new User();
        testUser.setUsername("Test Guy");
        testUser.setPassword("testpassword");
        testUser = userDao.createUser(testUser);

        Order order = new Order();
        order.setUserId(testUser.getId());
        LocalDate date = LocalDate.now();
        order.setOrderDate(date);
        order = orderDao.createOrder(order);

        order = orderDao.getUnsentOrderByUserId(testUser.getId());

        assertTrue(order != null);
        
        Goal g = new Goal();
        g.setName("test goal");
        g = goalDao.addGoal(g);

        Category c = new Category();
        c.setName("test cat");
        c = categoryDao.addCategory(c);
        
        Product p = new Product();
        p.setName("test product");
        p.setGoal(g);
        p.setCategory(c);
        p.setInventory(10);
        p.setPrice(new BigDecimal("10"));

        p = productDao.createProduct(p);

        Product p2 = new Product();
        p2.setName("test product 2");
        p2.setGoal(g);
        p2.setCategory(c);
        p2.setInventory(10);
        p2.setPrice(new BigDecimal("10"));

        p2 = productDao.createProduct(p2);

        orderDao.addToOrderProduct(order.getOrderId(), p.getProductId());

        orderDao.addToOrderProduct(order.getOrderId(), p2.getProductId());

        // products have been added to order so order must be re fetched
        order = orderDao.getOrderById(order.getOrderId());
        
        // get order with price calculated
        order = orderService.getUnsentOrderByUserId(testUser.getId());
        
        BigDecimal orderTotal = order.getTotalPrice();
        
        assertTrue(orderTotal != null);
    }
}
