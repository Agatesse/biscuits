package com.agathevaisse.biscuits.presentation;

import com.agathevaisse.biscuits.domain.mission.Mission;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.agathevaisse.biscuits")
@WebMvcTest
public class MissionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void createMission() {
    }

    @Test
    public void loadAllMissions() throws Exception {
        Mission mission1 = new Mission();
        mission1.setId(1);
        mission1.setimageURL("image");
        mission1.setAction("dormir");
        mission1.setDone(false);
        mission1.setBiscuitsToEarn(5);
        Mission mission2 = new Mission();
        Mission mission3 = new Mission();
        List<Mission> missions = new ArrayList<>();
        missions.add(mission1);
        missions.add(mission2);
        missions.add(mission3);

        mvc.perform(get("/api/missions/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[0].getId").value(mission1.getId()))
                .andExpect((ResultMatcher) jsonPath("$[0].getAction").value(mission1.getAction()));
    }

    @Test
    public void findMissionById() {
    }

    @Test
    public void findMissionByWord() {
    }

    @Test
    public void deleteMissionById() {
    }

    @Test
    public void deleteAllMissions() {
    }

    @Test
    public void updateMission() {
    }

    @Test
    public void isMissionDone() {
    }
}