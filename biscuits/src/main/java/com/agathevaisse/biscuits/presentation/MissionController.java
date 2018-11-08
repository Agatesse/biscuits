package com.agathevaisse.biscuits.presentation;

import com.agathevaisse.biscuits.application.MissionService;
import com.agathevaisse.biscuits.domain.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MissionController {

    @Autowired
    MissionService missionService;

    @PostMapping(value = "/missions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody Mission mission) {
        missionService.createMission(mission);
    }

    @GetMapping(value = "/missions")
    public List<Mission> loadAllTasks() {
        return missionService.loadAllMissions();
    }

    @GetMapping(value = "/missions/{id}")
    public Mission findTaskById(@PathVariable("id") int id) {
        return missionService.findMissionById(id);
    }

    @GetMapping(value = "/missions/search/{word}")
    public List<Mission> findTaskByWord(@PathVariable("word") String word) {
        return missionService.searchMissionsWithOneWord(word);
    }

    @DeleteMapping(value = "/missions/{id}")
    public void delete(@PathVariable("id") int id) {
        missionService.deleteMissionById(id);
    }

    @DeleteMapping(value = "/missions")
    public void delete() {
        missionService.deleteAllMissions();
    }

    @PatchMapping(value = "/missions/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") int id, @RequestBody String update) {
        missionService.updateMission(id, update);
    }

}
