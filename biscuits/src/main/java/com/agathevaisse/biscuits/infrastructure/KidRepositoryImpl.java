package com.agathevaisse.biscuits.infrastructure;

import com.agathevaisse.biscuits.domain.Kid;
import com.agathevaisse.biscuits.domain.KidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    private final static String INSERT_SQL = "insert into biscuit_kid(kid_nickname, kid_image, kid_biscuits) values (?, ?, ?)";
    private final static String SELECT_ALL_SQL = "select kid_id, kid_nickname, kid_image, kid_biscuits from biscuit_kid";
    private final static String SELECT_BY_ID_SQL = "select kid_id, kid_nickname, kid_image, kid_biscuits from biscuit_kid where kid_id = ?";
    private final static String DELETE_BY_ID_SQL = "delete from biscuit_kid where kid_id = ?";
    private final static String DELETE_ALL_SQL = "delete from biscuit_kid";
    private final static String UPDATE_BY_PUT_SQL = "update biscuit_kid set kid_nickname = ?, kid_biscuits = ?  where kid_id = ?";

    @Override
    public List<Kid> getKids() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new KidMapper());
    }

    @Override
    public void createKid(Kid kid) {
        jdbcTemplate.update(INSERT_SQL, kid.getNickname(), kid.getImageURL(), kid.getBiscuitsEarned());
    }

    @Override
    public Kid findKidById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new Object[]{id}, new KidMapper());
    }

    @Override
    public List<Kid> findKidsByNickname(String nickname) {
        return getKids().stream()
                .filter(kid -> kid.getNickname().contains(nickname))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteKidById(int id) {
        jdbcTemplate.update(DELETE_BY_ID_SQL, id);
    }

    @Override
    public void deleteKids() {
        jdbcTemplate.update(DELETE_ALL_SQL);
    }

    @Override
    public void updateKid(int id, Kid kid) {
        jdbcTemplate.update(UPDATE_BY_PUT_SQL, kid.getNickname(), kid.getBiscuitsEarned(), kid.getId());
    }
}
