package com.dp.supps.controllers;

import com.dp.supps.entities.Review;
import com.dp.supps.service.ReviewService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    
    private final ReviewService reviewServ;
    
    public ReviewController(ReviewService reviewServ) {
        this.reviewServ = reviewServ;
    }
    
    @GetMapping("/api/reviews/{id}")
    public ResponseEntity<Review> findReviewById(@PathVariable int id) {
        Review result = reviewServ.getReviewById(id);
        
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/reviews/product/{productId}")
    public List<Review> findReviewsByProductId(@PathVariable int productId) {
        return reviewServ.getReviewsByProductId(productId);
    }
    
    @PostMapping("/api/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public Review addReview(@RequestBody Review review) {
        return reviewServ.addReview(review);
    }
    
    @DeleteMapping("/api/reviews/{id}")
    public void deleteReviewById(@PathVariable int id) {
        reviewServ.deleteReviewById(id);
    }
}
