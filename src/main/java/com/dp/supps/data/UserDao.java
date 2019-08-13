package com.dp.supps.data;

import com.dp.supps.entities.User;
import java.util.List;

public interface UserDao {
    
    User getUserById(int id);

    List<User> getAllUsers();
    
    User getUserByUsername(String username);

    void updateUser(User user);

    void deleteUser(int id);

    User createUser(User user);
}
