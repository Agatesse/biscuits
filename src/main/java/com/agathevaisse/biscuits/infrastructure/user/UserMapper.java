package com.agathevaisse.biscuits.infrastructure.user;

import com.agathevaisse.biscuits.domain.authentication.user.RoleName;
import com.agathevaisse.biscuits.domain.authentication.user.User;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("user_username"));
        user.setEmail(rs.getString("user_email"));
        user.setPassword(rs.getString("user_password"));
        String role = (rs.getString("user_role"));
        user.setRole(RoleName.valueOf(role));
        return user;
    }
}
