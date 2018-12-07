package com.agathevaisse.biscuits.infrastructure.kid;

import com.agathevaisse.biscuits.domain.authentication.user.RoleName;
import com.agathevaisse.biscuits.domain.authentication.user.User;
import com.agathevaisse.biscuits.domain.kid.Kid;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KidMapper implements RowMapper<Kid> {
    public Kid mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Kid kid = new Kid();
        kid.setId(rs.getLong("kid_id"));
        kid.setNickname(rs.getString("kid_nickname"));
        kid.setImageURL();
        kid.setBiscuitsEarned(rs.getInt("kid_biscuits"));
        User user = new User();
        user.setId(rs.getLong("kid_user_id"));
        user.setUsername(rs.getString("user_username"));
        user.setEmail(rs.getString("user_email"));
        user.setPassword(rs.getString("user_password"));
        String role = (rs.getString("user_role"));
        user.setRole(RoleName.valueOf(role));
        kid.setUser(user);
        return kid;
    }
}

