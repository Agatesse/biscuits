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

    public List<Mission> getMissions() {return missionRepository.loadAllMissions();}
    public void createMission(Mission mission){missionRepository.createMission(mission);}
    public Mission findMissionById(int id) {return missionRepository.findMissionById(id);}
    public List<Mission> findMissionsByWord(String word) {return missionRepository.searchMissionsWithOneWord(word);}
    public void deleteMissionById(int id) {missionRepository.deleteMissionById(id);}
    public void deleteAllMissions() {missionRepository.deleteAllMissions();}
    public void updateMission(int id, Mission mission) {missionRepository.updateMission(id, mission);}
    public void isMissionDone (int id) {missionRepository.isMissionDone(id);}
}
