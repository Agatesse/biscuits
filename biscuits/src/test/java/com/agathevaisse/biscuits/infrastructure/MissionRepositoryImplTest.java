package com.agathevaisse.biscuits.infrastructure;

import com.agathevaisse.biscuits.configurationDataBaseTest.DataBaseTestConfiguration;
import com.agathevaisse.biscuits.domain.Mission;
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
        String sql = "create table if not exists biscuit_mission (mission_id bigint auto_increment primary key, mission_title text not null);";
        jdbcTemplate.update(sql);
    }

    @After
    public void runAfterEachTest() {
        String sql = "drop table if exists biscuit_mission;";
        jdbcTemplate.update(sql);
    }

    @Test
    public void should_load_all_missions() {
        String insertSQL1 = "insert into biscuit_mission(mission_title) values ('Mission1')";
        String insertSQL2 = "insert into biscuit_mission(mission_title) values ('Mission2')";
        String insertSQL3 = "insert into biscuit_mission(mission_title) values ('Mission3')";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(3);
        softly.assertThat(missionRepositoryImpl.loadAllMissions()).extracting("id", "title")
                .contains(tuple(1, "Mission1"),
                        tuple(2, "Mission2"),
                        tuple(3, "Mission3"));
        softly.assertAll();
    }

    @Test
    public void should_create_a_mission() {
        missionRepositoryImpl.createMission(new Mission("Préparer son goûter"));
        missionRepositoryImpl.createMission(new Mission("Ranger ses jouets"));
        missionRepositoryImpl.createMission(new Mission("Prendre ses vitamines"));
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(3);
        softly.assertThat(missionRepositoryImpl.loadAllMissions()).extracting("id", "title")
                .contains(tuple(1, "Préparer son goûter"),
                          tuple(2, "Ranger ses jouets"),
                          tuple(3, "Prendre ses vitamines"));
        softly.assertAll();
    }

    @Test
    public void should_find_a_Mission_with_its_id() {
        String insertSQL1 = "insert into biscuit_mission(mission_title) values ('Aller chercher du pain')";
        String insertSQL2 = "insert into biscuit_mission(mission_title) values ('Vider le lave-vaisselle')";
        String insertSQL3 = "insert into biscuit_mission(mission_title) values ('Finir son assiette')";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        assertThat(missionRepositoryImpl.findMissionById(2).getId()).isEqualTo(2);
    }

    @Test
    public void should_search_missions_with_a_word() {
        String insertSQL1 = "insert into biscuit_mission(mission_title) values ('Prendre une douche')";
        String insertSQL2 = "insert into biscuit_mission(mission_title) values ('Se coiffer les cheveux')";
        String insertSQL3 = "insert into biscuit_mission(mission_title) values ('Ranger les vêtements propres')";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        assertThat(missionRepositoryImpl.searchMissionsWithOneWord("les").size()).isEqualTo(2);
        assertThat(missionRepositoryImpl.searchMissionsWithOneWord("les")).extracting("title")
                .containsExactlyInAnyOrder("Se coiffer les cheveux", "Ranger les vêtements propres");
    }

    @Test
    public void should_count_missions() {
        String insertSQL1 = "insert into biscuit_mission(mission_title) values ('Aller chercher du pain')";
        String insertSQL2 = "insert into biscuit_mission(mission_title) values ('Vider le lave-vaisselle')";
        String insertSQL3 = "insert into biscuit_mission(mission_title) values ('Finir son assiette')";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(3);
    }

    @Test
    public void should_delete_a_mission_with_its_id() {
        String insertSQL = "insert into biscuit_mission(mission_title) values ('Débarrasser la table')";
        jdbcTemplate.update(insertSQL);
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(1);
        missionRepositoryImpl.deleteMissionById(1);
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(0);
    }

    @Test
    public void should_delete_all_missions() {
        String insertSQL1 = "insert into biscuit_mission(mission_title) values ('Manger des bonbons')";
        String insertSQL2 = "insert into biscuit_mission(mission_title) values ('Finir sa soupe')";
        String insertSQL3 = "insert into biscuit_mission(mission_title) values ('Nettoyer les vitres')";
        jdbcTemplate.update(insertSQL1);
        jdbcTemplate.update(insertSQL2);
        jdbcTemplate.update(insertSQL3);
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(3);
        missionRepositoryImpl.deleteAllMissions();
        assertThat(missionRepositoryImpl.loadAllMissions().size()).isEqualTo(0);
    }

    @Test
    public void should_update_a_mission() {
        String insertSQL = "insert into biscuit_mission(mission_title) values ('Se laver les dents')";
        jdbcTemplate.update(insertSQL);
        assertThat(missionRepositoryImpl.findMissionById(1)).extracting("title")
                .contains("Se laver les dents");
        missionRepositoryImpl.updateMission(1, "Se laver les mains");
        assertThat(missionRepositoryImpl.findMissionById(1)).extracting("title")
                .contains("Se laver les mains");
    }

}
