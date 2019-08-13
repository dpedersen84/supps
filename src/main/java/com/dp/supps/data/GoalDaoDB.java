package com.dp.supps.data;

import com.dp.supps.entities.Goal;
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
public class GoalDaoDB implements GoalDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Goal getGoalById(int id) {
        try {
            final String sql = "SELECT * FROM goal "
                    + "WHERE id = ?";

            return jdbc.queryForObject(sql,
                    new GoalMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Goal> getAllGoals() {
        final String sql = "SELECT * FROM goal";

        return jdbc.query(sql, new GoalMapper());
    }

    @Override
    @Transactional
    public Goal addGoal(Goal goal) {
        final String sql = "INSERT INTO goal (name) VALUES (?)";

        jdbc.update(sql, goal.getName());

        return goal;
    }

    @Override
    @Transactional
    public void deleteGoalById(int id) {
        final String deleteProduct = "DELETE FROM product WHERE goalId = ?";
        jdbc.update(deleteProduct, id);

        final String deleteGoal = "DELETE FROM goal WHERE id = ?";
        jdbc.update(deleteGoal, id);
    }

    public static final class GoalMapper implements RowMapper<Goal> {

        @Override
        public Goal mapRow(ResultSet rs, int index) throws SQLException {
            Goal goal = new Goal();
            goal.setId(rs.getInt("id"));
            goal.setName(rs.getString("name"));

            return goal;
        }
    }

}
