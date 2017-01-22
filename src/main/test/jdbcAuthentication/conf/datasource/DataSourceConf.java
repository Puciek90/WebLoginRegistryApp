package jdbcAuthentication.conf.datasource;// Created by Mateusz Płuciennik on 10.11.16.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConf {

//    @Primary
    @Bean(name = "dataSource")
    public DataSource testDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase embeddedDatabase = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("jdbcAuthentication/scripts/create-db.sql")
//                .addScript("jdbcAuthentication/scripts/insert-data.sql")
                .build();

        return embeddedDatabase;
    }
}
