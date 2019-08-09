package com.dp.supps.service;

import com.dp.supps.data.ReviewDaoDB;
import com.dp.supps.entities.Review;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewDaoDB reviewDao;
    
    @Autowired
    public ReviewService(ReviewDaoDB reviewDao) {
        this.reviewDao = reviewDao;
    }
    
    public Review getReviewById(int id) {
        return reviewDao.getReviewById(id);
    }
    
    public List<Review> getReviewsByProductId(int productId) {
        return reviewDao.getReviewsByProductId(productId);
    }
    
    public Review addReview(Review review) {
        return reviewDao.addReview(review);
    }
    
    public void deleteReviewById(int id) {
        reviewDao.deleteReviewById(id);
    }
}
