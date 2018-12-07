package com.agathevaisse.biscuits.infrastructure.kid;

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
        return kid;
    }
}

