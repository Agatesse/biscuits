package com.agathevaisse.biscuits.application;

import com.agathevaisse.biscuits.domain.UserAccountRepository;
import com.agathevaisse.biscuits.domain.authentication.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserAccountRepository userAccountRepository;

    public User getUser() {
        return userAccountRepository.getUser();
    }

    public void updateUser(int id, User user) {
        userAccountRepository.updateUser(id, user);
    }

    public void deleteUser(int id) {
        userAccountRepository.deleteUser(id);
    }
}




