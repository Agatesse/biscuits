package com.agathevaisse.biscuits.domain.authentication.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository{
    void createUser(User user);
    List<User> getUsers();
    User updateUser(Long id, User user);
    boolean deleteUser(int id);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}

