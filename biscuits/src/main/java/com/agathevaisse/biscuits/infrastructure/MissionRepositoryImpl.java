package com.agathevaisse.biscuits.infrastructure;

import com.agathevaisse.biscuits.domain.Mission;
import com.agathevaisse.biscuits.domain.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class MissionRepositoryImpl implements MissionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MissionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String INSERT_SQL = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values (?, ?, ?, ?)";
    private final String SELECT_ALL_SQL = "select mission_id, mission_action, mission_image, mission_done, mission_biscuits from biscuit_mission";
    private final String SELECT_BY_ID_SQL = "select mission_id, mission_action, mission_image, mission_done, mission_biscuits from biscuit_mission where mission_id = ?";
    private final String DELETE_BY_ID_SQL = "delete from biscuit_mission where mission_id = ?";
    private final String DELETE_ALL_SQL = "delete from biscuit_mission";
    private final String UPDATE_MISSION_BY_PUT_SQL = "update biscuit_mission set mission_action = ?, mission_biscuits = ?  where mission_id = ?";
    private final String UPDATE_ISDONE_BY_PATCH_SQL = "update biscuit_mission set mission_done = ? where mission_id = ?";

    @Override
    public List<Mission> loadAllMissions() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new MissionMapper());
    }

    @Override
    public void createMission(Mission mission) {
        mission.setimageURL("src/main/biscuits-ui/src/assets/images/secret-mission-stamp.jpg");
        mission.setDone(false);
        jdbcTemplate.update(INSERT_SQL, mission.getAction(), mission.getImageURL(), mission.isDone(), mission.getBiscuitsToEarn());
    }

    @Override
    public Mission findMissionById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new Object[]{id}, new MissionMapper());
    }

    @Override
    public List<Mission> searchMissionsWithOneWord(String word) {
        return loadAllMissions().stream()
                .filter(mission -> mission.getAction().contains(word))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMissionById(int id) {
        jdbcTemplate.update(DELETE_BY_ID_SQL, id);
    }

    @Override
    public void deleteAllMissions() {
        jdbcTemplate.update(DELETE_ALL_SQL);
    }

    @Override
    public void updateMission(int id, Mission mission) {
        jdbcTemplate.update(UPDATE_MISSION_BY_PUT_SQL, mission.getAction(), mission.getBiscuitsToEarn(), mission.getId());
    }

   @Override
    public void isMissionDone(int id) {
        Mission mission = findMissionById(id);
        mission.switchDone();
        jdbcTemplate.update(UPDATE_ISDONE_BY_PATCH_SQL, mission.isDone(),mission.getId());
    }
}

