package jdbcAuthentication.conf.servlet;// Created by Mateusz Płuciennik on 13.09.16.

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebbInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityWebbInitializer() {
        super(SecurityConfig.class);
    }
}
