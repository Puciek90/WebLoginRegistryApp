package jdbcAuthentication.foo.dao;// Created by Mateusz Płuciennik on 07.11.16.


import jdbcAuthentication.foo.model.WebUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface WebUserDao {
    UserDetails loadUserByUsername(String username);
    void saveUser(WebUser webUser);
    List<WebUser> showAllWebUsers();
    void deleteWebUserByName(String name);
    WebUser findByName(String name);
    void updateWebUser(WebUser webUser);
}
