/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.controllers;

import com.dp.supps.entities.Product;
import com.dp.supps.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dpede
 */
@RestController
public class ProductController {
 
    private final ProductService productServ;
    
    @Autowired
    public ProductController(ProductService productServ) {
        this.productServ = productServ;
    }
    
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productServ.findAll();
    }
    
}
