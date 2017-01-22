package jdbcAuthentication.conf.datasource;// Created by Mateusz Płuciennik on 13.09.16.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/hibernate");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("password");

        return driverManagerDataSource;
    }
}
