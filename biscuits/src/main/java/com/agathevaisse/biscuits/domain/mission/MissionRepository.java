package com.agathevaisse.biscuits.domain.mission;

import com.agathevaisse.biscuits.domain.mission.Mission;

import java.util.List;

public interface MissionRepository {

        List<Mission> loadAllMissions();
        boolean createMission(Mission mission);
        Mission findMissionById(Long id);
        List<Mission> searchMissionsWithOneWord(String word);
        boolean deleteMissionById(Long id);
        boolean deleteAllMissions();
        Mission updateMission(Long id, Mission mission);
        boolean completeMission(Long id);
        boolean cancelCompleteMission(Long id);
}
