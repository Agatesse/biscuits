package com.agathevaisse.biscuits.infrastructure.user;

import com.agathevaisse.biscuits.domain.authentication.user.User;
import com.agathevaisse.biscuits.domain.authentication.user.UserRepository;
import com.agathevaisse.biscuits.infrastructure.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String INSERT_SQL = "insert into biscuits_user(user_username, user_email, user_password, user_role) values (?, ?, ?, ?)";
    private final String SELECT_ALL_SQL = "select user_id, user_username, user_email, user_password, user_role from biscuits_user";
    private final String SELECT_BY_ID_SQL = "select user_id, user_username, user_email, user_password, user_role from biscuits_user where user_id = ?";
    private final String DELETE_BY_ID_SQL = "delete from biscuits_user where user_id = ?";
    private final String DELETE_ALL_SQL = "delete from biscuits_user";
    private final String UPDATE_BY_PUT_SQL = "update biscuits_user set user_username = ?, user_email = ?, user_password = ?  where user_id = ?";

    @Override
    public void createUser(User user) {
        jdbcTemplate.update(INSERT_SQL, user.getUsername(), user.getEmail(), user.getPassword(), user.getRole().toString());
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new UserMapper());
    }

    @Override
    public User updateUser(Long id, User user) {
        final Optional<User> optUser = findUserById(id);
        if (!optUser.isPresent()) {
            return null;
        }
        User oldUser = optUser.get();
        if (user.getUsername() == null) {
            user.setUsername(oldUser.getUsername());
        }
        if (user.getEmail() == null) {
            user.setEmail(oldUser.getEmail());
        }
        if (user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        }
        jdbcTemplate.update(UPDATE_BY_PUT_SQL, user.getUsername(), user.getEmail(), user.getPassword(), id);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(DELETE_BY_ID_SQL, id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return getUsers().stream()
                .filter(opt -> opt.getUsername().trim().toLowerCase().contains(username.trim().toLowerCase()))
                .findFirst();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return getUsers().stream()
                .filter(optUser -> optUser.getId().equals(id))
                .findFirst();
    }

    @Override
    public Boolean existsByUsername(String username) {
        List<User> users = getUsers().stream()
                .filter(user -> user.getUsername().trim().toLowerCase().contains(username.trim().toLowerCase()))
                .collect(Collectors.toList());
        return users.size() != 0;
    }

    @Override
    public Boolean existsByEmail(String email) {
        List<User> users = getUsers().stream()
                .filter(user -> user.getEmail().trim().toLowerCase().contains(email.trim().toLowerCase()))
                .collect(Collectors.toList());
        return users.size() != 0;
    }
}