package com.agathevaisse.biscuits.domain.kid;

import com.agathevaisse.biscuits.domain.kid.Kid;

import java.util.List;

public interface KidRepository {
    List<Kid> getKids();

    void createKid(Kid kid);

    Kid findKidById(int id);

    List<Kid> findKidsByNickname(String nickname);

    void deleteKidById(int id);

    void deleteKids();

    void updateKid(int id, Kid kid);
}
