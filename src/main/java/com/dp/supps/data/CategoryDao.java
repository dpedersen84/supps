package com.dp.supps.data;

import com.dp.supps.entities.Category;
import java.util.List;

public interface CategoryDao {

    Category getCategoryById(int id);

    List<Category> getAllCategories();
    
    Category addCategory(Category category);

    void deleteCategoryById(int id);
}
