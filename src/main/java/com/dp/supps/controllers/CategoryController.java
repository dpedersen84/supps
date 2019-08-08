/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.controllers;

import com.dp.supps.entities.Category;
import com.dp.supps.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dpede
 */
@RestController
public class CategoryController {
    
    private final CategoryService categoryServ;
    
    @Autowired
    public CategoryController(CategoryService categoryServ) {
        this.categoryServ = categoryServ;
    }
    
    @GetMapping("/api/category")
    public List<Category> getAllCategories() {
        return categoryServ.allCategories();
    }
    
    @GetMapping("/api/category/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryServ.getCategoryById(id);
    }
    
    @PostMapping("/api/category/")
    public Category addCategory(@RequestBody Category category) {
        return categoryServ.addCategory(category);
    }
    
    @DeleteMapping("/api/category/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryServ.deleteCategoryById(id);
    }
}
