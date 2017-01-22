package jdbcAuthentication.foo.service;// Created by Mateusz Płuciennik on 07.11.16.

import jdbcAuthentication.foo.dao.WebUserDao;
import jdbcAuthentication.foo.model.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("webUserService")
@Transactional
public class WebUserServiceImpl implements WebUserService, UserDetailsService {

    @Autowired
    private WebUserDao webUserDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return webUserDao.loadUserByUsername(username);
    }

    public void saveUser(WebUser webUser) {
        webUserDao.saveUser(webUser);
    }


    public List<WebUser> showAllWebUsers() {
        return webUserDao.showAllWebUsers();
    }

    public void deleteWebUserByName(String name) {
        webUserDao.deleteWebUserByName(name);
    }

    public WebUser findByName(String name) {
        return webUserDao.findByName(name);
    }

    public void updateWebUser(WebUser webUser) {
        webUserDao.updateWebUser(webUser);
    }
}
