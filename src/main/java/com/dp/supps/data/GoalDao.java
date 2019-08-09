/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.data;

import com.dp.supps.entities.Goal;
import java.util.List;

/**
 *
 * @author Dan
 */
public interface GoalDao {

    Goal getGoalById(int id);

    List<Goal> getAllGoals();

    Goal addGoal(Goal goal);

    void deleteGoalById(int id);
}
