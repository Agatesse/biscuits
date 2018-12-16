package com.agathevaisse.biscuits.infrastructure.kid;

import com.agathevaisse.biscuits.domain.authentication.user.User;
import com.agathevaisse.biscuits.domain.kid.Kid;
import com.agathevaisse.biscuits.domain.kid.KidRepository;
import com.agathevaisse.biscuits.infrastructure.kid.KidMapper;
import com.agathevaisse.biscuits.infrastructure.mission.MissionRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class KidRepositoryImpl implements KidRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public KidRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final Logger logger = LoggerFactory.getLogger(KidRepositoryImpl.class);

    private final static String INSERT_SQL = "insert into biscuits_kid(kid_nickname, kid_image, kid_biscuits, kid_user_id) values (?, ?, ?, ?)";
    private final static String SELECT_ALL_SQL = "select * from biscuits_kid inner join biscuits_user on biscuits_kid.kid_user_id = biscuits_user.user_id";
    private final static String SELECT_BY_ID_SQL = "select * from biscuits_kid inner join biscuits_user on biscuits_kid.kid_user_id = biscuits_user.user_id where kid_id = ?";
    private final static String DELETE_BY_ID_SQL = "delete from biscuits_kid where kid_id = ?";
    private final static String DELETE_ALL_SQL = "delete from biscuits_kid";
    private final static String UPDATE_BY_PUT_SQL = "update biscuits_kid set kid_nickname = ?, kid_biscuits = ?  where kid_id = ?";

    @Override
    public List<Kid> getKids() {
        try {
            return jdbcTemplate.query(SELECT_ALL_SQL, new KidMapper());
        } catch (Exception e) {
            logger.error("No kid found! -> Message: {} ", e);
            return null;
        }
    }

    @Override
    public boolean createKid(Kid kid) {
        try {
            kid.setImageURL();
            kid.setBiscuitsEarned(0);
            jdbcTemplate.update(INSERT_SQL, kid.getNickname(), kid.getImageURL(), kid.getBiscuitsEarned(), kid.getUser().getId());
            return true;
        } catch (Exception e) {
            logger.error("Kid creation failed! -> Message: {} ", e);
            return false;
        }
    }

    @Override
    public Kid findKidById(Long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new Object[]{id}, new KidMapper());
        } catch (Exception e) {
            logger.error("No kid found with this id! -> Message: {} ", e);
            return null;
        }
    }

    @Override
    public List<Kid> findKidsByUser(Long id) {
        try {
            return getKids().stream()
                    .filter(kid -> kid.getUser().getId().equals(id))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("No kid found for this user! -> Message: {} ", e);
            return null;
        }
    }

    @Override
    public List<Kid> findKidsByNickname(String nickname) {
        try {
            return getKids().stream()
                    .filter(kid -> kid.getNickname().trim().toLowerCase().contains(nickname.trim().toLowerCase()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("No kid found with this nickname! -> Message: {} ", e);
            return null;
        }

    }

    @Override
    public boolean deleteKidById(Long id) {
        try {
            jdbcTemplate.update(DELETE_BY_ID_SQL, id);
            return true;
        } catch (Exception e) {
            logger.error("Delete kid failed! -> Message: {} ", e);
            return false;
        }

    }

    @Override
    public boolean deleteKids() {
        try {
            jdbcTemplate.update(DELETE_ALL_SQL);
            return true;
        } catch (Exception e) {
            logger.error("Delete kids failed! -> Message: {} ", e);
            return false;
        }

    }

    @Override
    public Kid updateKid(Long id, Kid kid) {
        try {
            kid.setId(id);
            final Optional<Kid> optKid = Optional.ofNullable(findKidById(id));
            if (!optKid.isPresent()) {
                return null;
            }
            Kid oldKid = optKid.get();
            if (kid.getNickname() == null) {
                kid.setNickname(oldKid.getNickname());
            }
            if (kid.getImageURL() == null) {
                kid.setImageURL();
            }
            if (kid.getBiscuitsEarned() == 0) {
                kid.setBiscuitsEarned(oldKid.getBiscuitsEarned());
            }
            if(kid.getUser() == null) {
                kid.setUser((oldKid.getUser()));
            }
            jdbcTemplate.update(UPDATE_BY_PUT_SQL, kid.getNickname(), kid.getBiscuitsEarned(), kid.getId());
            return kid;

        } catch (Exception e) {
            logger.error("Update kid failed! -> Message: {} ", e);
            return null;
        }

    }
}