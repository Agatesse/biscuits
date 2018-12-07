package com.agathevaisse.biscuits.presentation;

import com.agathevaisse.biscuits.application.MissionService;
import com.agathevaisse.biscuits.domain.mission.Mission;
import com.agathevaisse.biscuits.infrastructure.mission.MissionRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MissionController {

    @Autowired
    MissionService missionService;

    private static final Logger logger = LoggerFactory.getLogger(MissionController.class);

    @PostMapping(value = "api/missions/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean createMission(@RequestBody final Mission mission) {
        return missionService.createMission(mission);
    }

    @GetMapping(value = "api/missions/all")
    public List<Mission> getMissions() {
        return missionService.getMissions();
    }

    @GetMapping(value = "api/missions/{id}")
    public Mission findMissionById(@PathVariable("id") Long id) {
        return missionService.findMissionById(id);
    }

    @GetMapping(value = "api/missions/find-missions-by-word/{word}")
    public List<Mission> findMissionByWord(@PathVariable("word") String word) {
        return missionService.findMissionsByWord(word);
    }

    @GetMapping(value = "api/missions/find-missions-by-kid/{id}")
    public List<Mission> findMissionsByKid(@PathVariable("id") Long id) {
        return missionService.findMissionsByKid(id);
    }

    @DeleteMapping(value = "api/missions/delete/{id}")
    public boolean deleteMissionById(@PathVariable("id") Long id) {
        return missionService.deleteMissionById(id);
    }

    @DeleteMapping(value = "api/missions/delete/all")
    public boolean deleteMissions() {
        return missionService.deleteMissions();
    }

    @PutMapping(value = "api/missions/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mission updateMission(@PathVariable("id") Long id, @RequestBody Mission mission) {
        return missionService.updateMission(id, mission);
    }

    @PatchMapping(value = "api/missions/complete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean completeMission(@PathVariable("id") Long id) {
        return missionService.completeMission(id);
    }

    @PatchMapping(value = "api/missions/cancel-complete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean cancelCompleteMission(@PathVariable("id") Long id) {
        return missionService.cancelCompleteMission(id);
    }

}
