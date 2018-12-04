package com.agathevaisse.biscuits.application;

import com.agathevaisse.biscuits.domain.Kid;
import com.agathevaisse.biscuits.domain.KidRepository;
import com.agathevaisse.biscuits.domain.Mission;
import com.agathevaisse.biscuits.domain.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KidService {
    @Autowired
    KidRepository kidRepository;

    public List<Kid> getKids() {
        return kidRepository.getKids();
    }

    public void createKid(Kid kid) {
        kidRepository.createKid(kid);
    }

    public Kid findKidById(int id) {
        return kidRepository.findKidById(id);
    }

    public List<Kid> findKidsByNickname(String nickname) {
        return kidRepository.findKidsByNickname(nickname);
    }

    public void deleteKidById(int id) {
        kidRepository.deleteKidById(id);
    }

    public void deleteKids() {
        kidRepository.deleteKids();
    }

    public void updateKid(int id, Kid kid) {
        kidRepository.updateKid(id, kid);
    }
}