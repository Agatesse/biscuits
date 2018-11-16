package com.agathevaisse.biscuits.infrastructure;

import com.agathevaisse.biscuits.domain.Mission;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MissionMapper implements RowMapper<Mission> {
    public Mission mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Mission mission = new Mission();
        mission.setId(rs.getInt("mission_id"));
        mission.setAction(rs.getString("mission_action"));
        mission.setimageURL(rs.getString("mission_image"));
        mission.setDone(rs.getBoolean("mission_done"));
        mission.setBiscuitsToEarn(rs.getInt("mission_biscuits"));
        return mission;
    }
}
