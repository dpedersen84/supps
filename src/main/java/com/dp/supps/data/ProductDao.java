package com.dp.supps.data;

import com.dp.supps.entities.Product;
import java.util.List;

public interface ProductDao {
    
    List<Product> getAllProducts();
    
    Product findByProductId(int productId);
    
    Product addProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProductById(int productId);
}
