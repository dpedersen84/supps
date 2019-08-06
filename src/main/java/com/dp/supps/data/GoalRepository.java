/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.data;

import com.dp.supps.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dpede
 */
@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
    
}
