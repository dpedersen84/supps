package com.dp.supps.data;

import com.dp.supps.entities.Product;
import com.dp.supps.entities.Review;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewDaoDBTest {

    @Autowired
    ReviewDaoDB reviewDao;

    @Autowired
    ProductDaoDB productDao;

    public ReviewDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Product> products = productDao.getAllProducts();

        if (products != null || products.size() > 0) {
            for (Product p : products) {
                List<Review> reviews = reviewDao
                        .getReviewsByProductId(p.getProductId());

                for (Review r : reviews) {
                    reviewDao.deleteReviewById(r.getId());
                }
            }
        }
    }

    @Test
    public void testGetReviewById() {
        Review r = new Review();
        r.setProductId(1);
        r.setRating(5);
        r.setDescription("Test Description");

        reviewDao.addReview(r);

        Review fromDao = reviewDao.getReviewById(r.getId());

        assertEquals(fromDao, r);
    }

    @Test
    public void testAddAndGetReviewsByProductId() {
        Review r = new Review();
        r.setProductId(1);
        r.setRating(5);
        r.setDescription("Test Description");

        reviewDao.addReview(r);
        
        Review r2 = new Review();
        r2.setProductId(1);
        r2.setRating(5);
        r2.setDescription("Test Description");

        reviewDao.addReview(r2);
        
        List<Review> reviews = reviewDao.getReviewsByProductId(1);
        
        assertEquals(2, reviews.size());
    }

    @Test
    public void testDeleteReviewById() {
        Review r = new Review();
        r.setProductId(1);
        r.setRating(5);
        r.setDescription("Test Description");

        reviewDao.addReview(r);
        
        Review r2 = new Review();
        r2.setProductId(1);
        r2.setRating(5);
        r2.setDescription("Test Description");

        reviewDao.addReview(r2);
        
        List<Review> reviews = reviewDao.getReviewsByProductId(1);
        
        assertEquals(2, reviews.size());
        
        reviewDao.deleteReviewById(r2.getId());
        
        List<Review> reviewsAfterDelete = reviewDao.getReviewsByProductId(1);
        
        assertFalse(reviewsAfterDelete.contains(r2));
        
        assertEquals(1, reviewsAfterDelete.size());
    }
}
