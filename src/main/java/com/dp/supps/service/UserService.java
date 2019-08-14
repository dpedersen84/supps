package com.dp.supps.service;

import com.dp.supps.data.UserDaoDB;
import com.dp.supps.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDaoDB userDao;
    
    @Autowired
    public UserService(UserDaoDB userDao) {
        this.userDao = userDao;
    }
    
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    public User createUser(User user) {
        // only I may be the admin!
        user.setIsAdmin(false);
        
        if(user.isIsAdmin()) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }
        
        return userDao.createUser(user);
    }
}