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

@RestController
public class ProductController {

    private final ProductService productServ;

    @Autowired
    public ProductController(ProductService productServ) {
        this.productServ = productServ;
    }

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productServ.getAllProducts();
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id) {
        Product result = productServ.findProductById(id);

        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/api/products/goal/{goalId}")
    public List<Product> findProductsByGoalId(@PathVariable int goalId) {
        return productServ.allProductsByGoalId(goalId);
    }
    
    @GetMapping("/api/products/category/{categoryId}")
    public List<Product> findProductsByCategoryId(@PathVariable int categoryId) {
        return productServ.allProductsByCategoryId(categoryId);
    }
    
    @PostMapping("/api/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        return productServ.addProduct(product);
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product product) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != product.getProductId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (id == product.getProductId()) {
            productServ.updateProduct(product);
            response = new ResponseEntity(HttpStatus.OK);
        }

        return response;
    }

    @DeleteMapping("/api/products/{id}")
    public void delete(@PathVariable int id) {
        productServ.deleteProductById(id);
    }
}
