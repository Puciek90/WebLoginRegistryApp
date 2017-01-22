package jdbcAuthentication.conf.datasource;// Created by Mateusz Płuciennik on 03.01.17.

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Profile("testdata")
@Configuration
public class TestDataConfiguration {

    @Transactional
    @PostConstruct
    public void insert() {

    }
}
