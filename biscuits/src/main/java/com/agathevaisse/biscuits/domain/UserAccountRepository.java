package com.agathevaisse.biscuits.domain;

import com.agathevaisse.biscuits.domain.authentication.user.User;

public interface UserAccountRepository {
    User getUser();
    void updateUser(int id, User user);
    void deleteUser(int id);
}
