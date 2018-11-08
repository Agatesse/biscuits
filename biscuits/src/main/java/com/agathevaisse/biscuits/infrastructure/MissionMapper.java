package com.agathevaisse.biscuits.infrastructure;

import com.agathevaisse.biscuits.domain.Mission;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MissionMapper implements RowMapper<Mission> {
    public Mission mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Mission mission = new Mission();
        mission.setId(rs.getInt("mission_id"));
        mission.setTitle(rs.getString("mission_title"));
        return mission;
    }
}
