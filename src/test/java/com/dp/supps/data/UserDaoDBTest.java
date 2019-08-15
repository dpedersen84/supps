package com.dp.supps.data;

import com.dp.supps.entities.User;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoDBTest {
    
    @Autowired
    UserDaoDB userDao;
    
    public UserDaoDBTest() {
    }
     
    @BeforeEach
    public void setUp() {
        List<User> users = userDao.getAllUsers();
        
        for (User u : users) {
            userDao.deleteUserById(u.getId());
        }
    }

    @Test
    public void testCreateAndGetUserById() {
        User testUser = new User();
        testUser.setUsername("Test Username");
        testUser.setPassword("testword");
        testUser = userDao.createUser(testUser);
        
        User userFromDao = userDao.getUserById(testUser.getId());
        
        assertEquals(userFromDao.getId(), testUser.getId());
    }

    @Test
    public void testGetAllUsers() {
        User testUser = new User();
        testUser.setUsername("Test Username");
        testUser.setPassword("testword");
        testUser = userDao.createUser(testUser);
        
        User testUser2 = new User();
        testUser2.setUsername("Test Username");
        testUser2.setPassword("testword");
        testUser2 = userDao.createUser(testUser2);
        
        List<User> users = userDao.getAllUsers();
        
        assertEquals(2, users.size());
    }

    @Test
    public void testDeleteUserById() {
        User testUser = new User();
        testUser.setUsername("Test Username");
        testUser.setPassword("testword");
        testUser = userDao.createUser(testUser);
        
        User testUser2 = new User();
        testUser2.setUsername("Test Username");
        testUser2.setPassword("testword");
        testUser2 = userDao.createUser(testUser2);
        
        List<User> users = userDao.getAllUsers();
        
        assertEquals(2, users.size());
        
        userDao.deleteUserById(testUser2.getId());
        
        users = userDao.getAllUsers();
        
        assertTrue(users.size() == 1);   
    }
}
