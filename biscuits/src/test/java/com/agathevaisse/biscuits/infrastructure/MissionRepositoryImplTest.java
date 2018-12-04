package com.agathevaisse.biscuits.infrastructure;

import com.agathevaisse.biscuits.configurationDataBaseTest.DataBaseTestConfiguration;
import com.agathevaisse.biscuits.domain.mission.Mission;
import com.agathevaisse.biscuits.infrastructure.mission.MissionRepositoryImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataBaseTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@ComponentScan
public class MissionRepositoryImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MissionRepositoryImpl missionRepositoryImpl;

    @Before
    public void setUp() throws Exception {
        String sql = "create table if not exists biscuit_mission (mission_id bigint auto_increment primary key, mission_action text not null, mission_image text not null, mission_done boolean not null, mission_biscuits integer not null);";
        jdbcTemplate.update(sql);
    }

    @After
    public void runAfterEachTest() {
        String sql = "drop table if exists biscuit_mission;";
        jdbcTemplate.update(sql);
    }

    @Test
    public void should_load_all_missions() {
        String insertSQL1 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Se laver les dents', 'images/secret-mission-stamp.jpg', false, 5)";
        String insertSQL2 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Se laver les mains', 'images/secret-mission-stamp.jpg', false, 8)";
        String insertSQL3 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Se laver les pieds', 'images/secret-mission-stamp.jpg', false, 15)";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(3);
        softly.assertThat(missionRepositoryImpl.loadAllMissions()).extracting("id", "action", "imageURL", "isDone", "biscuitsToEarn")
                .contains(tuple(1, "Se laver les dents", "images/secret-mission-stamp.jpg", false, 5),
                        tuple(2, "Se laver les mains", "images/secret-mission-stamp.jpg", false, 8),
                        tuple(3, "Se laver les pieds", "images/secret-mission-stamp.jpg", false, 15));
        softly.assertAll();
    }

    @Test
    public void should_create_a_mission() {
        missionRepositoryImpl.createMission(new Mission("Préparer son goûter", 10));
        missionRepositoryImpl.createMission(new Mission("Ranger ses jouets", 3));
        missionRepositoryImpl.createMission(new Mission("Prendre ses vitamines", 1));
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(3);
        softly.assertThat(missionRepositoryImpl.loadAllMissions()).extracting("id", "action", "imageURL", "isDone", "biscuitsToEarn")
                .contains(tuple(1, "Préparer son goûter", "src/main/biscuits-ui/src/assets/images/secret-mission-stamp.jpg", false, 10),
                          tuple(2, "Ranger ses jouets", "src/main/biscuits-ui/src/assets/images/secret-mission-stamp.jpg", false, 3),
                          tuple(3, "Prendre ses vitamines", "src/main/biscuits-ui/src/assets/images/secret-mission-stamp.jpg", false, 1));
        softly.assertAll();
    }

    @Test
    public void should_find_a_Mission_with_its_id() {
        String insertSQL1 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Aller chercher du pain', 'images/secret-mission-stamp.jpg', false, 20)";
        String insertSQL2 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Vider le lave-vaisselle', 'images/secret-mission-stamp.jpg', false, 12)";
        String insertSQL3 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Finir son assiette', 'images/secret-mission-stamp.jpg', false, 10)";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        assertThat(missionRepositoryImpl.findMissionById(2).getId()).isEqualTo(2);
    }

    @Test
    public void should_search_missions_with_a_word() {
        String insertSQL1 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Prendre une douche', 'images/secret-mission-stamp.jpg', false, 3)";
        String insertSQL2 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Se coiffer les cheveux', 'images/secret-mission-stamp.jpg', false, 8)";
        String insertSQL3 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Ranger les vêtements propres', 'images/secret-mission-stamp.jpg', false, 16)";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        assertThat(missionRepositoryImpl.searchMissionsWithOneWord("les").size()).isEqualTo(2);
        assertThat(missionRepositoryImpl.searchMissionsWithOneWord("les")).extracting("action")
                .containsExactlyInAnyOrder("Se coiffer les cheveux", "Ranger les vêtements propres");
    }

    @Test
    public void should_count_missions() {
        String insertSQL1 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Aller chercher du pain', 'images/secret-mission-stamp.jpg', false, 7)";
        String insertSQL2 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Vider le lave-vaisselle', 'images/secret-mission-stamp.jpg', false, 9)";
        String insertSQL3 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Finir son assiette', 'images/secret-mission-stamp.jpg', false, 10)";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(3);
    }

    @Test
    public void should_delete_a_mission_with_its_id() {
        String insertSQL = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Débarrasser la table', 'images/secret-mission-stamp.jpg', false, 7,)";
        jdbcTemplate.update(insertSQL);
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(1);
        missionRepositoryImpl.deleteMissionById(1);
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(0);
    }

    @Test
    public void should_delete_all_missions() {
        String insertSQL1 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Manger des bonbons', 'images/secret-mission-stamp.jpg', false, 42)";
        String insertSQL2 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Finir sa soupe', 'images/secret-mission-stamp.jpg', false, 6)";
        String insertSQL3 = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Nettoyer les vitres', 'images/secret-mission-stamp.jpg', false, 16)";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(3);
        missionRepositoryImpl.deleteAllMissions();
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(0);
    }

   /* @Test
    public void should_update_a_mission() {
        String insertSQL = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Se laver les dents', 'images/secret-mission-stamp.jpg', false, 5)";
        jdbcTemplate.update(insertSQL);
        assertThat(missionRepositoryImpl.findMissionById(1)).extracting("action", "imageURL", "isDone", "biscuitsToEarn")
                .contains("Se laver les dents", "images/secret-mission-stamp.jpg", false, 5);
        missionRepositoryImpl.updateMission(1, "Se laver les mains", missionRepositoryImpl.findMissionById(1).isDone(), 8);
        assertThat(missionRepositoryImpl.findMissionById(1)).extracting("action", "imageURL", "isDone", "biscuitsToEarn")
                .contains("Se laver les mains", "images/secret-mission-stamp.jpg", false, 8);
    }*/

      @Test
    public void should_update_is_done_attribute() {
        String insertSQL = "insert into biscuit_mission(mission_action, mission_image, mission_done, mission_biscuits) values ('Se laver les dents', 'images/secret-mission-stamp.jpg', false, 5)";
        jdbcTemplate.update(insertSQL);
        assertThat(missionRepositoryImpl.findMissionById(1)).extracting("action", "imageURL", "isDone", "biscuitsToEarn")
                .contains("Se laver les dents", "images/secret-mission-stamp.jpg", false, 5);
        missionRepositoryImpl.isMissionDone(1);
        assertThat(missionRepositoryImpl.findMissionById(1)).extracting("action", "imageURL", "isDone", "biscuitsToEarn")
                .contains("Se laver les dents", "images/secret-mission-stamp.jpg", true, 5);
        missionRepositoryImpl.isMissionDone(1);
        assertThat(missionRepositoryImpl.findMissionById(1)).extracting("action", "imageURL", "isDone", "biscuitsToEarn")
                .contains("Se laver les dents", "images/secret-mission-stamp.jpg", false, 5);
    }

}
