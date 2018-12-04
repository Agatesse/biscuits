package com.agathevaisse.biscuits.presentation;

import com.agathevaisse.biscuits.application.MissionService;
import com.agathevaisse.biscuits.domain.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MissionController {

    @Autowired
    MissionService missionService;

    @PostMapping(value = "api/missions/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMission(@RequestBody Mission mission) {
        missionService.createMission(mission);
    }

    @GetMapping(value = "api/missions/all")
    public List<Mission> getMissions() {
        return missionService.getMissions();
    }

    @GetMapping(value = "api/missions/{id}")
    public Mission findMissionById(@PathVariable("id") int id) {
        return missionService.findMissionById(id);
    }

    @GetMapping(value = "api/missions/search/{word}")
    public List<Mission> findMissionByWord(@PathVariable("word") String word) {
        return missionService.findMissionsByWord(word);
    }

    @DeleteMapping(value = "api/missions/delete/{id}")
    public void deleteMissionById(@PathVariable("id") int id) {
        missionService.deleteMissionById(id);
    }

    @DeleteMapping(value = "api/missions/delete")
    public void deleteAllMissions() {
        missionService.deleteAllMissions();
    }

    @PatchMapping(value = "api/missions/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMission(@PathVariable("id") int id, @RequestBody Mission mission) {
        missionService.updateMission(id, mission);
    }

    @PatchMapping(value = "api/missions/is-done/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void isMissionDone(@PathVariable("id") int id) {
        missionService.isMissionDone(id);
    }

}
