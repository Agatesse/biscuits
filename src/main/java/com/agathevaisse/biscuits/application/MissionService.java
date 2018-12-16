package com.agathevaisse.biscuits.application;

import com.agathevaisse.biscuits.domain.mission.Mission;
import com.agathevaisse.biscuits.domain.mission.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MissionService {

    @Autowired
    MissionRepository missionRepository;

    public List<Mission> getMissions() {return missionRepository.getMissions();}
    public boolean createMission(Mission mission){return missionRepository.createMission(mission);}
    public Mission findMissionById(Long id) {return missionRepository.findMissionById(id);}
    public List<Mission> findMissionsByWord(String word) {return missionRepository.findMissionsByWord(word);}
    public List<Mission> findMissionsByKid(Long id) { return missionRepository.findMissionsByKid(id);}
    public boolean deleteMissionById(Long id) {return missionRepository.deleteMissionById(id);}
    public boolean deleteMissions() {return missionRepository.deleteMissions();}
    public Mission updateMission(Long id, Mission mission) {return missionRepository.updateMission(id, mission);}
    public boolean completeMission(Long id) {return missionRepository.completeMission(id);}
    public boolean cancelCompleteMission(Long id) {return missionRepository.cancelCompleteMission(id);}
}
