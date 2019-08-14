package com.dp.supps.data;

import com.dp.supps.entities.Goal;
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
public class GoalDaoDBTest {

    @Autowired
    GoalDaoDB goalDao;

    @Autowired
    ProductDaoDB productDao;

    @Autowired
    ReviewDaoDB reviewDao;

    public GoalDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Product> products = productDao.getAllProducts();

        for (Product p : products) {
            List<Review> reviews = reviewDao
                    .getReviewsByProductId(p.getProductId());

            for (Review r : reviews) {
                reviewDao.deleteReviewById(r.getId());
            }
        }

        for (Product p : products) {
            productDao.deleteProductById(p.getProductId());
        }
        
        List<Goal> goals = goalDao.getAllGoals();

        for (Goal g : goals) {
            goalDao.deleteGoalById(g.getId());
        }
    }

    @Test
    public void testGetGoalById() {
        Goal g = new Goal();
        g.setName("Test Goal");
        goalDao.addGoal(g);

        Goal g2 = new Goal();
        g2.setName("Test Goal 2");
        goalDao.addGoal(g2);

        Goal fromDao = goalDao.getGoalById(g.getId());

        assertEquals(fromDao, g);
    }

    @Test
    public void testAddAndGetAllGoals() {
        Goal g = new Goal();
        g.setName("Test Goal");
        goalDao.addGoal(g);

        Goal g2 = new Goal();
        g2.setName("Test Goal 2");
        goalDao.addGoal(g2);

        List<Goal> goals = goalDao.getAllGoals();

        assertEquals(2, goals.size());
    }

    @Test
    public void testDeleteGoalById() {
        Goal g = new Goal();
        g.setName("Test Goal");
        goalDao.addGoal(g);

        Goal g2 = new Goal();
        g2.setName("Test Goal 2");
        goalDao.addGoal(g2);

        List<Goal> goals = goalDao.getAllGoals();

        assertEquals(2, goals.size());

        goalDao.deleteGoalById(g2.getId());

        List<Goal> goalsAfterDelete = goalDao.getAllGoals();

        assertEquals(1, goalsAfterDelete.size());
    }
}
