package jdbcAuthentication.conf.dao;// Created by Mateusz Płuciennik on 14.01.17.

import jdbcAuthentication.conf.datasource.DataSourceConfig;
import jdbcAuthentication.conf.datasource.HibernateConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {DataSourceConfig.class, HibernateConfiguration.class})
@ComponentScan({"jdbcAuthentication.foo.dao"})
public class DaoConfiguration {
}
