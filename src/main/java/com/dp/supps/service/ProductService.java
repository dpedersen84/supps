/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.service;

import com.dp.supps.data.ProductDaoDB;
import com.dp.supps.entities.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dpede
 */
@Service
public class ProductService {

    private final ProductDaoDB productDao;

    @Autowired
    public ProductService(ProductDaoDB productDao) {
        this.productDao = productDao;
    }
    
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
    
    public List<Product> allProductsByGoalId(int goalId) {
        return productDao.getProductsByGoalId(goalId);
    }
    
    public List<Product> allProductsByCategoryId(int categoryId) {
        return productDao.getProductsByCategoryId(categoryId);
    }
 
    public Product findProductById(int productId) {
        return productDao.getProductById(productId);
    }
    
    public Product addProduct(Product product) {
        return productDao.createProduct(product);
    }
    
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }
    
    public void deleteProductById(int productId) {
        productDao.deleteProductById(productId);
    }
}
