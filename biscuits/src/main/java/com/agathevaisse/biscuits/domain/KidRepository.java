package com.agathevaisse.biscuits.domain;

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
