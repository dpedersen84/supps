package com.dp.supps.controllers;

import com.dp.supps.entities.Category;
import com.dp.supps.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    
    private final CategoryService categoryServ;
    
    @Autowired
    public CategoryController(CategoryService categoryServ) {
        this.categoryServ = categoryServ;
    }
    
    @GetMapping("/api/category")
    public List<Category> getCategories() {
        return categoryServ.allCategories();
    }
    
    @GetMapping("/api/category/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryServ.getCategoryById(id);
    }
    
    @PostMapping("/api/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) {
        return categoryServ.addCategory(category);
    }
    
    @DeleteMapping("/api/category/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryServ.deleteCategoryById(id);
    }
}
