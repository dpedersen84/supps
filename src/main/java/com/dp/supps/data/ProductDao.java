package com.dp.supps.data;

import com.dp.supps.entities.Product;
import java.util.List;

public interface ProductDao {
    
    List<Product> getAllProducts();
    
    List<Product> getProductsByGoalId(int goalId);
    
    List<Product> getProductsByCategoryId(int categoryId);
    
    Product getProductById(int productId);
    
    Product createProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProductById(int productId);
}
