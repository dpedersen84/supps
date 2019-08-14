package com.dp.supps.controllers;

import com.dp.supps.entities.Goal;
import com.dp.supps.service.GoalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GoalController {
    
    private final GoalService goalServ;

    @Autowired
    public GoalController(GoalService goalServ) {
        this.goalServ = goalServ;
    }

    @GetMapping("/api/goals")
    public List<Goal> getAllGoals() {
        return goalServ.getAllGoals();
    }

    @GetMapping("/api/goals/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable int id) {
        Goal result = goalServ.getGoalById(id);

        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/goals")
    @ResponseStatus(HttpStatus.CREATED)
    public Goal addGoal(@RequestBody Goal goal) {
        return goalServ.addGoal(goal);
    }

    @DeleteMapping("/api/goals/{id}")
    public void deleteGoal(@PathVariable int id) {
        goalServ.deleteGoalById(id);
    }
}
