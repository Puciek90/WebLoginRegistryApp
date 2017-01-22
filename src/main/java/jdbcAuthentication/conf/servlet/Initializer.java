package jdbcAuthentication.conf.servlet;// Created by Mateusz Płuciennik on 13.09.16.

import jdbcAuthentication.conf.root.RootConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Initializer implements WebApplicationInitializer{
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(RootConfig.class);
        annotationConfigWebApplicationContext.register(ServletConfiguration.class);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
