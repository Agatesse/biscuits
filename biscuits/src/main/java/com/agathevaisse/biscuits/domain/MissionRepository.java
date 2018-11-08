package com.agathevaisse.biscuits.domain;

import java.util.List;

public interface MissionRepository {

        List<Mission> loadAllMissions();
        void createMission(Mission mission);
        Mission findMissionById(int id);
        List<Mission> searchMissionsWithOneWord(String word);
        void deleteMissionById(int id);
        void deleteAllMissions();
        void updateMission(int id, String update);
}
