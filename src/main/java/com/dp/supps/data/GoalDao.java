package com.dp.supps.data;

import com.dp.supps.entities.Goal;
import java.util.List;

public interface GoalDao {

    Goal getGoalById(int id);

    List<Goal> getAllGoals();

    Goal createGoal(Goal goal);

    void deleteGoalById(int id);
}
