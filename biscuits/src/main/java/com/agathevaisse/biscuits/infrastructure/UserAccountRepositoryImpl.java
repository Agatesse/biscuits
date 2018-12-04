package com.agathevaisse.biscuits.infrastructure;

import com.agathevaisse.biscuits.domain.UserAccountRepository;
import com.agathevaisse.biscuits.domain.authentication.user.User;

public class UserAccountRepositoryImpl implements UserAccountRepository {

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void updateUser(int id, User user) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
