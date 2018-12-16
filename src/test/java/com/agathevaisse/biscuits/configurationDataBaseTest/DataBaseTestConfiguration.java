package com.agathevaisse.biscuits.configurationDataBaseTest;

import com.agathevaisse.biscuits.domain.mission.MissionRepository;
import com.agathevaisse.biscuits.infrastructure.mission.MissionRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataBaseTestConfiguration {

    @Bean
    public EmbeddedDatabase embeddedDatabase(){
        return new EmbeddedDatabaseBuilder()
                //.addScript("schema-test.sql")
                //.addScript("data.sql")
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(EmbeddedDatabase embeddedDatabase) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    public MissionRepository missionRepository(){
        return new MissionRepositoryImpl(this.jdbcTemplate(this.embeddedDatabase()));
    }
}
