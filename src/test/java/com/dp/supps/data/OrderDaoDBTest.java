package com.dp.supps.data;

import com.dp.supps.entities.Category;
import com.dp.supps.entities.Goal;
import com.dp.supps.entities.Order;
import com.dp.supps.entities.Product;
import com.dp.supps.entities.Review;
import com.dp.supps.entities.User;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class OrderDaoDBTest {

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

    public OrderDaoDBTest() {
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
    public void testCreateAndGetAllOrders() {
        User testUser = new User();
        testUser.setUsername("Test Guy");
        testUser.setPassword("testpassword");
        testUser = userDao.createUser(testUser);

        Order testOrder = new Order();
        testOrder.setUserId(testUser.getId());
        LocalDate date = LocalDate.now();
        testOrder.setOrderDate(date);
        testOrder = orderDao.createOrder(testOrder);

        assertTrue(testOrder != null);

        List<Order> orders = orderDao.getAllOrders();

        assertTrue(orders.size() == 1);
    }

    @Test
    public void testGetSentAndUnsentOrders() {
        User testUser = new User();
        testUser.setUsername("Test Guy");
        testUser.setPassword("testpassword");
        testUser = userDao.createUser(testUser);

        Order unsentTestOrder = new Order();
        unsentTestOrder.setUserId(testUser.getId());
        LocalDate date = LocalDate.now();
        unsentTestOrder.setOrderDate(date);
        unsentTestOrder = orderDao.createOrder(unsentTestOrder);

        Order sentTestOrder = new Order();
        sentTestOrder.setUserId(testUser.getId());
        sentTestOrder.setOrderDate(date);
        sentTestOrder.setOrderSent(true);
        sentTestOrder = orderDao.createOrder(sentTestOrder);

        List<Order> allOrders = orderDao.getAllOrders();

        List<Order> sentOrders = orderDao.getSentOrders();

        List<Order> unSentOrders = orderDao.getUnsentOrders();

        assertTrue(allOrders.size() == 2);

        assertTrue(sentOrders.size() == 1);

        assertTrue(unSentOrders.size() == 1);
    }

    @Test
    public void testGetUnsentOrderByUserId() {
        User testUser = new User();
        testUser.setUsername("Test Guy");
        testUser.setPassword("testpassword");
        testUser = userDao.createUser(testUser);

        Order unsentTestOrder = new Order();
        unsentTestOrder.setUserId(testUser.getId());
        LocalDate date = LocalDate.now();
        unsentTestOrder.setOrderDate(date);
        unsentTestOrder = orderDao.createOrder(unsentTestOrder);

        Order order = orderDao.getUnsentOrderByUserId(testUser.getId());

        assertTrue(order != null);
    }

    @Test
    public void testGetOrderById() {
        User testUser = new User();
        testUser.setUsername("Test Guy");
        testUser.setPassword("testpassword");
        testUser = userDao.createUser(testUser);

        Order testOrder = new Order();
        testOrder.setUserId(testUser.getId());
        LocalDate date = LocalDate.now();
        testOrder.setOrderDate(date);
        testOrder = orderDao.createOrder(testOrder);

        assertTrue(testOrder != null);

        Order order = orderDao.getOrderById(testOrder.getOrderId());

        assertTrue(order != null);

        assertTrue(testOrder.getOrderId() == order.getOrderId());
    }

    @Test
    public void testCompleteOrder() {
        User testUser = new User();
        testUser.setUsername("Test Guy");
        testUser.setPassword("testpassword");
        testUser = userDao.createUser(testUser);

        Order testOrder = new Order();
        testOrder.setUserId(testUser.getId());
        LocalDate date = LocalDate.now();
        testOrder.setOrderDate(date);
        testOrder = orderDao.createOrder(testOrder);

        assertEquals(false, testOrder.isOrderSent());

        testOrder = orderDao.completeOrder(testOrder);

        assertTrue(testOrder.isOrderSent());
    }

    @Test
    public void testDeleteOrderById() {
        User testUser = new User();
        testUser.setUsername("Test Guy");
        testUser.setPassword("testpassword");
        testUser = userDao.createUser(testUser);

        Order testOrder = new Order();
        testOrder.setUserId(testUser.getId());
        LocalDate date = LocalDate.now();
        testOrder.setOrderDate(date);
        testOrder = orderDao.createOrder(testOrder);

        Order testOrder2 = new Order();
        testOrder2.setUserId(testUser.getId());
        testOrder2.setOrderDate(date);
        testOrder2 = orderDao.createOrder(testOrder2);

        List<Order> orders = orderDao.getAllOrders();

        assertEquals(2, orders.size());

        orderDao.deleteOrderById(testOrder.getOrderId());

        orders = orderDao.getAllOrders();

        assertEquals(1, orders.size());
    }

    @Test
    public void testAddToOrderProduct() {
        User testUser = new User();
        testUser.setUsername("Test Guy");
        testUser.setPassword("testpassword");
        testUser = userDao.createUser(testUser);

        Order testOrder = new Order();
        testOrder.setUserId(testUser.getId());
        LocalDate date = LocalDate.now();
        testOrder.setOrderDate(date);
        testOrder = orderDao.createOrder(testOrder);

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

        orderDao.addToOrderProduct(testOrder.getOrderId(), p.getProductId());

        orderDao.addToOrderProduct(testOrder.getOrderId(), p2.getProductId());

        // products have been added to order so order must be re fetched
        testOrder = orderDao.getOrderById(testOrder.getOrderId());
        
        List<Product> productsFromOrder = testOrder.getProducts();

        assertEquals(2, productsFromOrder.size());
    }

    @Test
    public void testRemoveFromOrderProduct() {
        User testUser = new User();
        testUser.setUsername("Test Guy");
        testUser.setPassword("testpassword");
        testUser = userDao.createUser(testUser);

        Order testOrder = new Order();
        testOrder.setUserId(testUser.getId());
        LocalDate date = LocalDate.now();
        testOrder.setOrderDate(date);
        testOrder = orderDao.createOrder(testOrder);

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

        orderDao.addToOrderProduct(testOrder.getOrderId(), p.getProductId());

        orderDao.addToOrderProduct(testOrder.getOrderId(), p2.getProductId());

        // products have been added to order so order must be re fetched
        testOrder = orderDao.getOrderById(testOrder.getOrderId());
        
        List<Product> productsFromOrder = testOrder.getProducts();

        assertEquals(2, productsFromOrder.size());
        
        orderDao.removeFromOrderProduct(testOrder.getOrderId(), p2.getProductId());
        
        // products have been removed from order so order must be re fetched
        testOrder = orderDao.getOrderById(testOrder.getOrderId());
        
        productsFromOrder = testOrder.getProducts();

        assertEquals(1, productsFromOrder.size());
    }
}
