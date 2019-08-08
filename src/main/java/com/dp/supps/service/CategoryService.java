/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.service;

import com.dp.supps.data.CategoryDaoDB;
import com.dp.supps.entities.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dpede
 */
@Service
public class CategoryService {
    
    private final CategoryDaoDB categoryDao;
    
    @Autowired
    public CategoryService(CategoryDaoDB categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    public List<Category> allCategories() {
        return categoryDao.getAllCategories();
    }
    
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }
    
    public Category addCategory(Category category) {
        return categoryDao.addCategory(category);
    }
    
    public void deleteCategoryById(int id) {
        categoryDao.deleteCategoryById(id);
    }
}