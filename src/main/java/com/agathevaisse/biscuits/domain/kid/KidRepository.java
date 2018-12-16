package com.agathevaisse.biscuits.domain.kid;

import java.util.List;

public interface KidRepository {
    List<Kid> getKids();

    boolean createKid(Kid kid);

    Kid findKidById(Long id);

    List<Kid> findKidsByUser(Long id);

    List<Kid> findKidsByNickname(String nickname);

    boolean deleteKidById(Long id);

    boolean deleteKids();

    Kid updateKid(Long id, Kid kid);
}
