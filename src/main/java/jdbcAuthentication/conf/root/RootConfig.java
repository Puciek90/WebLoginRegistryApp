package jdbcAuthentication.conf.root;// Created by Mateusz Płuciennik on 10.11.16.

import jdbcAuthentication.conf.dao.DaoConfiguration;
import jdbcAuthentication.conf.service.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ServiceConfiguration.class, DaoConfiguration.class})
public class RootConfig {
}
