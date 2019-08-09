package com.dp.supps.data;

import com.dp.supps.entities.Review;
import java.util.List;

public interface ReviewDao {
    
    Review getReviewById(int id);
    
    List<Review> getReviewsByProductId(int productId);
    
    Review addReview(Review review);
    
    void deleteReviewById(int id);
}
