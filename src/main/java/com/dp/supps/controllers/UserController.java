package com.dp.supps.controllers;

import com.dp.supps.entities.User;
import com.dp.supps.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    private final UserService userServ;
    
    @Autowired
    public UserController(UserService userServ) {
        this.userServ = userServ;
    }
    
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User result = userServ.getUserById(id);
        
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userServ.getAllUsers();
    }
    
    @GetMapping("/api/users/{username}/{password}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username, @PathVariable String password) {
        User result = userServ.getUserByUsername(username, password);
        
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userServ.createUser(user);
    }
}
