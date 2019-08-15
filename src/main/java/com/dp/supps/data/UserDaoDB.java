package com.dp.supps.data;

import com.dp.supps.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoDB implements UserDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public User getUserById(int id) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
            return user;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USERS = "SELECT * FROM users";
        List<User> users = jdbc.query(SELECT_ALL_USERS, new UserMapper());
        
        return users;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        final String sql = "INSERT INTO users(username, password,"
                + " isAdmin, role) VALUES(?, ?, ?, ?) RETURNING id";
        
        int id = jdbc.queryForObject(sql, new Object[]{user.getUsername(),
            user.getPassword(), user.isIsAdmin(), user.getRole()},
                Integer.class);
        
        user.setId(id);

        return user;
    }
    
    @Override
    @Transactional
    public void deleteUserById(int id) {
        final String deleteOrder = "DELETE FROM orders WHERE userid = ?";
        
        jdbc.update(deleteOrder, id);
        
        final String deleteUser = "DELETE FROM users WHERE id = ?";
        
        jdbc.update(deleteUser, id);
    }
    
    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setIsAdmin(rs.getBoolean("isAdmin"));
            user.setRole(rs.getString("role"));
            return user;
        }
    }
}
