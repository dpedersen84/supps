/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.service;

import com.dp.supps.data.ProductRepository;
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

    private final ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }
    
    public List<Product> findAll() {
        return productRepo.findAll();
    }
    
    public Product findById(int id) {
        return productRepo.findById(id).orElse(null);
    }
    
    public Product addProduct(Product p) {
        return productRepo.save(p);
    }
    
    public void deleteById(int id) {
        productRepo.deleteById(id);
    }
}
