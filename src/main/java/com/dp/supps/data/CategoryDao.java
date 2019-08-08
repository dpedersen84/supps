/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.data;

import com.dp.supps.entities.Category;
import java.util.List;

/**
 *
 * @author dpede
 */
public interface CategoryDao {

    Category getCategoryById(int id);

    List<Category> getAllCategories();
    
    Category addCategory(Category category);

    void deleteCategoryById(int id);
}
