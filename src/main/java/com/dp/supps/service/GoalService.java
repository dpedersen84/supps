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
    
    public List<Goal> allGoals() {
        return goalDao.getAllGoals();
    }
    
    public Goal getGoalById(int id) {
        return goalDao.getGoalById(id);
    }
    
    public Goal addGoal(Goal goal) {
        return goalDao.addGoal(goal);
    }
    
    public void deleteGoalById(int id) {
        goalDao.deleteGoalById(id);
    }
    
}
