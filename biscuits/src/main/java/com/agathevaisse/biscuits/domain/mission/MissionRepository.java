package com.agathevaisse.biscuits.domain.mission;

import java.util.List;

public interface MissionRepository {

        List<Mission> getMissions();
        boolean createMission(Mission mission);
        Mission findMissionById(Long id);
        List<Mission> findMissionsByWord(String word);
        boolean deleteMissionById(Long id);
        boolean deleteMissions();
        Mission updateMission(Long id, Mission mission);
        boolean completeMission(Long id);
        boolean cancelCompleteMission(Long id);
}
