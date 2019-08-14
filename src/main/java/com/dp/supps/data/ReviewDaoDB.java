package com.dp.supps.data;

import com.dp.supps.entities.Review;
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
public class ReviewDaoDB implements ReviewDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Review getReviewById(int id) {
        try {
            final String sql = "SELECT * FROM review WHERE id = ?";

            return jdbc.queryForObject(sql, new ReviewMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Review> getReviewsByProductId(int productId) {
        final String sql = "SELECT * FROM review WHERE productId = ?";

        return jdbc.query(sql, new ReviewMapper(), productId);
    }

    @Override
    @Transactional
    public Review addReview(Review review) {
        final String sql = "INSERT INTO review (productid, rating, description)"
                + " VALUES (?, ?, ?) RETURNING id";

        int id = jdbc.queryForObject(sql, new Object[]{review.getProductId(),
            review.getRating(), review.getDescription()}, Integer.class);
        
        review.setId(id);

        return review;
    }

    @Override
    @Transactional
    public void deleteReviewById(int id) {
        final String sql = "DELETE FROM review WHERE id = ?";

        jdbc.update(sql, id);
    }

    public static final class ReviewMapper implements RowMapper<Review> {

        @Override
        public Review mapRow(ResultSet rs, int index) throws SQLException {
            Review r = new Review();
            r.setId(rs.getInt("id"));
            r.setRating(rs.getInt("rating"));
            r.setProductId(rs.getInt("productId"));
            r.setDescription(rs.getString("description"));

            return r;
        }
    }
}
