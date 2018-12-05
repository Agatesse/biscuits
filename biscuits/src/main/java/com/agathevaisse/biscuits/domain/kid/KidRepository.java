package com.agathevaisse.biscuits.domain.kid;

import com.agathevaisse.biscuits.domain.kid.Kid;

import java.util.List;

public interface KidRepository {
    List<Kid> getKids();

    void createKid(Kid kid);

    Kid findKidById(Long id);

    List<Kid> findKidsByNickname(String nickname);

    void deleteKidById(Long id);

    void deleteKids();

    void updateKid(Long id, Kid kid);
}
