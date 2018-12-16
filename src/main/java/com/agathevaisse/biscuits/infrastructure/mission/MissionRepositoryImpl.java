package com.agathevaisse.biscuits.infrastructure.mission;

import com.agathevaisse.biscuits.application.KidService;
import com.agathevaisse.biscuits.domain.kid.Kid;
import com.agathevaisse.biscuits.domain.mission.Mission;
import com.agathevaisse.biscuits.domain.mission.MissionRepository;
import com.agathevaisse.biscuits.infrastructure.mission.MissionMapper;
import com.agathevaisse.biscuits.security.jwt.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.jaas.LoginExceptionResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Autowired
    KidService kidService;

    private static final Logger logger = LoggerFactory.getLogger(MissionRepositoryImpl.class);

    private final static String INSERT_SQL = "insert into biscuits_mission(mission_action, mission_image, mission_done, mission_biscuits, mission_kid_id) values (?, ?, ?, ?, ?)";
    private final static String SELECT_ALL_SQL = "select * from biscuits_mission inner join biscuits_kid on biscuits_mission.mission_kid_id = biscuits_kid.kid_id";
    private final static String SELECT_BY_ID_SQL = "select * from biscuits_mission inner join biscuits_kid on biscuits_mission.mission_kid_id = biscuits_kid.kid_id where mission_id = ?";
    private final static String DELETE_BY_ID_SQL = "delete from biscuits_mission where mission_id = ?";
    private final static String DELETE_ALL_SQL = "delete from biscuits_mission";
    private final static String UPDATE_MISSION_BY_PUT_SQL = "UPDATE public.biscuits_mission SET mission_action=?, mission_image=?, mission_done=?, mission_biscuits=?, mission_kid_id=? WHERE mission_id=?";
    private final static String UPDATE_ISDONE_BY_PATCH_SQL = "update biscuits_mission set mission_done = ? where mission_id = ?";


    @Override
    public List<Mission> getMissions() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new MissionMapper());
    }

    @Override
    public boolean createMission(Mission mission) {
        try {
            mission.setImageURL();
            mission.setDone(false);
            jdbcTemplate.update(INSERT_SQL, mission.getAction(), mission.getImageURL(), mission.isDone(), mission.getBiscuitsToEarn(), mission.getKid().getId());
            return true;
        } catch (Exception e) {
            logger.error("Add Mission Failed! -> Message: {} ", e);
            return false;
        }
    }

    @Override
    public Mission findMissionById(Long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new Object[]{id}, new MissionMapper());
        } catch (Exception e) {
            logger.error("No mission found with this id! -> Message {}", e);
            return null;
        }
    }

    @Override
    public List<Mission> findMissionsByWord(String word) {
        try {
            return getMissions().stream()
                    .filter(mission -> mission.getAction().trim().toLowerCase().contains(word.trim().toLowerCase()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("No mission found with this word! -> Message {}", e);
            return null;
        }
    }

    @Override
    public List<Mission> findMissionsByKid(Long id) {
        try {
            return getMissions().stream()
                    .filter(mission -> mission.getKid().getId().equals(id))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("No mission found for this kid! -> Message {}", e);
            return null;
        }
    }

    @Override
    public boolean deleteMissionById(Long id) {
        try {
            jdbcTemplate.update(DELETE_BY_ID_SQL, id);
            return true;
        } catch (Exception e) {
            logger.error("Delete Mission Failed! -> Message: {} ", e);
            return false;
        }
    }

    @Override
    public boolean deleteMissions() {
        try {
            jdbcTemplate.update(DELETE_ALL_SQL);
            return true;
        } catch (Exception e) {
            logger.error("Delete missions Failed! -> Message: {} ", e);
            return false;
        }

    }

    @Override
    public Mission updateMission(Long id, Mission mission) {
        try {
            mission.setId(id);
            final Optional<Mission> optMission = Optional.ofNullable(findMissionById(id));
            if (!optMission.isPresent()) {
                return null;
            }
            Mission oldMission = optMission.get();
            if (null == mission.getAction()) {
                mission.setAction(oldMission.getAction());
            }
            if (mission.getImageURL() == null) {
                mission.setImageURL();
            }
            if (0 == mission.getBiscuitsToEarn()) {
                mission.setBiscuitsToEarn(oldMission.getBiscuitsToEarn());
            }
            mission.setDone(oldMission.isDone());
            mission.setKid(oldMission.getKid());
            jdbcTemplate.update(UPDATE_MISSION_BY_PUT_SQL, mission.getAction(), mission.getImageURL(), mission.isDone(), mission.getBiscuitsToEarn(), mission.getKid().getId(), mission.getId());
            return findMissionById(id);
        } catch (Exception e) {
            logger.error("Update mission Failed! -> Message: {} ", e);
            return null;
        }
    }

   @Override
    public boolean completeMission(Long id) {
        try {
            Mission mission = findMissionById(id);
            if (mission.isDone()) {
                return false;
            }
            mission.setDone(true);
            jdbcTemplate.update(UPDATE_ISDONE_BY_PATCH_SQL, mission.isDone(),mission.getId());
            addBiscuits(mission.getBiscuitsToEarn(), mission.getKid().getId());
            return true;
        } catch (Exception e) {
            logger.error("Complete Mission failed! -> Message: {} ", e);
            return false;
        }
    }

    @Override
    public boolean cancelCompleteMission(Long id) {
        try {
            Mission mission = findMissionById(id);
            if (!mission.isDone()) {
                return false;
            }
            mission.setDone(false);
            jdbcTemplate.update(UPDATE_ISDONE_BY_PATCH_SQL, mission.isDone(),mission.getId());
            removeBiscuits(mission.getKid().getId(), mission.getBiscuitsToEarn());
            return true;
        } catch (Exception e) {
            logger.error("Cancel Complete Mission failed! -> Message: {} ", e);
            return false;
        }
    }

    private boolean addBiscuits(int biscuitsToEarn, Long kidId) {
        try {
            Kid kid = kidService.findKidById(kidId);
            int biscuitsFromKid = kid.getBiscuitsEarned();
            int result = biscuitsFromKid + biscuitsToEarn;
            kid.setBiscuitsEarned(result);
            kidService.updateKid(kid.getId(), kid);
            return true;
        } catch (Exception e) {
            logger.error("Add biscuits failed! -> Message: {} ", e);
            return false;
        }
    }

    private boolean removeBiscuits(Long kidId, int biscuitsToEarn) {
        try {
            Kid kid = kidService.findKidById(kidId);
            int biscuitsFromKid = kid.getBiscuitsEarned();
            int result = biscuitsFromKid - biscuitsToEarn;
            kid.setBiscuitsEarned(result);
            kidService.updateKid(kid.getId(), kid);
            return false;
        } catch (Exception e) {
            logger.error("Add biscuits failed! -> Message: {} ", e);
            return false;
        }
    }
}

