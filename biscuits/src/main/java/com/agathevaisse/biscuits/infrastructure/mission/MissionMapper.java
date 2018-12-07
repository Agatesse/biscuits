package com.agathevaisse.biscuits.infrastructure.mission;

import com.agathevaisse.biscuits.domain.kid.Kid;
import com.agathevaisse.biscuits.domain.mission.Mission;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MissionMapper implements RowMapper<Mission> {
    public Mission mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Mission mission = new Mission();
        mission.setId(rs.getLong("mission_id"));
        mission.setAction(rs.getString("mission_action"));
        mission.setImageURL();
        mission.setDone(rs.getBoolean("mission_done"));
        mission.setBiscuitsToEarn(rs.getInt("mission_biscuits"));
        Kid kid = new Kid();
        kid.setId(rs.getLong("mission_kid_id"));
        kid.setNickname(rs.getString("kid_nickname"));
        kid.setImageURL();
        kid.setBiscuitsEarned(rs.getInt("kid_biscuits"));
        mission.setKid(kid);
        return mission;
    }
}
