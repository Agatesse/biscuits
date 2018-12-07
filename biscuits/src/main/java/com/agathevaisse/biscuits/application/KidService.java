package com.agathevaisse.biscuits.application;

import com.agathevaisse.biscuits.domain.kid.Kid;
import com.agathevaisse.biscuits.domain.kid.KidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KidService {
    @Autowired
    KidRepository kidRepository;

    public List<Kid> getKids() {
        return kidRepository.getKids();
    }

    public boolean createKid(Kid kid) {
         return kidRepository.createKid(kid);
    }

    public Kid findKidById(Long id) {
        return kidRepository.findKidById(id);
    }

    public List<Kid> findKidsByNickname(String nickname) {
        return kidRepository.findKidsByNickname(nickname);
    }

    public boolean deleteKidById(Long id) {
        return kidRepository.deleteKidById(id);
    }

    public boolean deleteKids() {
        return kidRepository.deleteKids();
    }

    public Kid updateKid(Long id, Kid kid) {
        return kidRepository.updateKid(id, kid);
    }
}