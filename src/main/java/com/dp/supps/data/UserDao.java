package com.dp.supps.data;

import com.dp.supps.entities.User;
import java.util.List;

public interface UserDao {
    
    User getUserById(int id);

    List<User> getAllUsers();
    
    User getUserByUsername(String username);

    User createUser(User user);
}
