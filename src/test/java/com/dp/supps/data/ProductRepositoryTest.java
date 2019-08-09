/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.data;

import com.dp.supps.entities.Category;
import com.dp.supps.entities.Goal;
import com.dp.supps.entities.Product;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dpede
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    
//    @Autowired
//    CategoryRepository categoryRepo;
//    
//    @Autowired
//    GoalRepository goalRepo;
//    
//    @Autowired
//    ProductRepository productRepo;
//    
//    public ProductRepositoryTest() {
//    }
//    
//    @Before
//    public void setUp() {
//        List<Goal> allGoals = goalRepo.findAll();
//        for (Goal goal : allGoals) {
//            goalRepo.delete(goal);
//        }
//        
//        List<Category> allCategories = categoryRepo.findAll();
//        for (Category c : allCategories) {
//            categoryRepo.delete(c);
//        }
//        
//        List<Product> allProducts = productRepo.findAll();
//        for (Product product : allProducts) {
//            productRepo.deleteById(product.getId());
//        }
//    }
//
//    @Test
//    public void testAddAndFindAll() {
//        Product a = new Product();
//        a.setId(1);
//        a.setName("Test Product One");
//        a.setPrice(new BigDecimal("1.00"));
//        a.setInventory(10);
//        
//        Goal g = new Goal();
//        g.setId(1);
//        g.setName("Test Goal");
//        goalRepo.save(g);
//        a.setGoal(g);
//        
//        Category c = new Category();
//        c.setId(1);
//        c.setName("Test Category");
//        categoryRepo.save(c);
//        a.setCategory(c);
//        
//        a.setDescription("Test Description");
//        a.setImage("Test Image");
//        
//        productRepo.save(a);
//        
//        Product b = new Product();
//        b.setId(2);
//        b.setName("Test Product Two");
//        b.setPrice(new BigDecimal("1.00"));
//        b.setInventory(10);
//        b.setGoal(g);
//        b.setCategory(c);
//        b.setImage("Test Image");
//        b.setDescription("Test Description");
//        b.setImage("Test Image");
//        
//        productRepo.save(b);
//        
//        List<Product> products = productRepo.findAll();
//        
//        assertEquals(2, products.size());
//    }
//    @Test
//    public void testFindById() {
//        Product a = new Product();
//        a.setId(1);
//        a.setName("Test Product One");
//        a.setPrice(new BigDecimal("1.00"));
//        a.setInventory(10);
//        
//        Goal g = new Goal();
//        g.setId(1);
//        g.setName("Test Goal");
//        goalRepo.save(g);
//        a.setGoal(g);
//        
//        Category c = new Category();
//        c.setId(1);
//        c.setName("Test Category");
//        categoryRepo.save(c);
//        a.setCategory(c);
//        
//        a.setDescription("Test Description");
//        a.setImage("Test Image");
//        
//        productRepo.save(a);
//        
//        Product fromDao = productRepo.findById(1).orElse(null);
//        
//        assertEquals(a.getName(), fromDao.getName());
//    }
}
