package com.dp.supps.service;

import com.dp.supps.data.GoalDaoDB;
import com.dp.supps.entities.Goal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
    
    private final GoalDaoDB goalDao;
    
    @Autowired
    public GoalService(GoalDaoDB goalDao) {
        this.goalDao = goalDao;
    }
    
    public List<Goal> getAllGoals() {
        return goalDao.getAllGoals();
    }
    
    public Goal getGoalById(int id) {
        return goalDao.getGoalById(id);
    }
    
    public Goal createGoal(Goal goal) {
        // set goal id
        List<Goal> goals = getAllGoals();

        Goal last = goals.get(goals.size() - 1);

        goal.setId(last.getId() + 1);
        
        return goalDao.createGoal(goal);
    }
    
    public void deleteGoalById(int id) {
        goalDao.deleteGoalById(id);
    }
}