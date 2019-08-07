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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id) {
        Product result = productServ.findById(id);

        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        return productServ.addProduct(product);
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product product) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != product.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (id == product.getId()) {
            productServ.addProduct(product);
            response = new ResponseEntity(HttpStatus.OK);
        }

        return response;
    }

    @DeleteMapping("/api/products/{id}")
    public void delete(@PathVariable int id) {
        productServ.deleteById(id);
    }
}
