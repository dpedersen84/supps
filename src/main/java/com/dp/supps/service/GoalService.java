/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.service;

import com.dp.supps.data.GoalRepository;
import com.dp.supps.entities.Goal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dpede
 */
@Service
public class GoalService {
    
    private final GoalRepository goalRepo;
    
    @Autowired
    public GoalService(GoalRepository goalRepo) {
        this.goalRepo = goalRepo;
    }
    
    public List<Goal> findAll() {
        return goalRepo.findAll();
    }
    
    public Goal findById(int id) {
        return goalRepo.findById(id).orElse(null);
    }
    
    public Goal addGoal(Goal g) {
        return goalRepo.save(g);
    }
    
    public void deleteById(int id) {
        goalRepo.deleteById(id);
    }
}
