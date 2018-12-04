package com.agathevaisse.biscuits.domain;

import com.agathevaisse.biscuits.domain.mission.Mission;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissionTest {

    @Test
    public void switchDone() {
        Mission mission = new Mission();
        mission.setId(1);
        mission.setAction("Jouer à la Switch");
        mission.setimageURL("image");
        mission.setDone(false);
        mission.setBiscuitsToEarn(5);
        mission.switchDone();
        assertThat(mission.isDone()).isEqualTo(true);
        mission.switchDone();
        assertThat(mission.isDone()).isEqualTo(false);
    }
}