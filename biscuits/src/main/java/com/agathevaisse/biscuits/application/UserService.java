package com.agathevaisse.biscuits.application;

import com.agathevaisse.biscuits.domain.authentication.user.User;
import com.agathevaisse.biscuits.domain.authentication.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void createUser(User user) {userRepository.createUser(user);}

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public List<String> getUsernames() {
        return userRepository.getUsernames();
    }

    public List<String> getEmails() {
        return userRepository.getEmails();
    }

    public User updateUser(Long id, User user) {
        return userRepository.updateUser(id, user);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    public Optional<User> findUserByUsername(String username) { return userRepository.findUserByUsername(username);}

    public Optional<User> findUserById(Long id) { return userRepository.findUserById(id);}

    public Boolean existsByUsername(String username) { return userRepository.existsByUsername(username);}

    public Boolean existsByEmail(String email) { return userRepository.existsByEmail(email);}
}




