package jdbcAuthentication.conf.servlet;// Created by Mateusz Płuciennik on 12.10.16.

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler{
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        List<SimpleGrantedAuthority> authorities = Collections.unmodifiableList(
                asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        HttpSession session = httpServletRequest.getSession();
        org.springframework.security.core.userdetails.User authUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        session.setAttribute("user", authUser);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        String contextPath = httpServletRequest.getContextPath();

        if (authUser.getAuthorities().contains(authorities.get(1))) {
            httpServletResponse.sendRedirect(contextPath + "/admin");
        }

        if (authUser.getAuthorities().contains(authorities.get(0))) {
            httpServletResponse.sendRedirect(contextPath + "/user");
        }
    }
}
