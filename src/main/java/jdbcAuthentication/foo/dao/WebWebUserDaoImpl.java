package jdbcAuthentication.foo.dao;// Created by Mateusz Płuciennik on 07.11.16.

import jdbcAuthentication.foo.model.WebUser;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository("webUserDao")
public class WebWebUserDaoImpl extends AbstractDAO implements WebUserDao {

    public void saveUser(WebUser webUser) {
        persist(webUser);
    }

    public List<WebUser> showAllWebUsers() {
        return getSession().createCriteria(WebUser.class).list();
    }

    public void deleteWebUserByName(String name) {
        WebUser webUser = new WebUser();
        webUser.setName(name);
        getSession().delete(webUser);
    }

    public WebUser findByName(String name) throws UsernameNotFoundException{
        Criteria criteria = getSession().createCriteria(WebUser.class);
        criteria.add(Restrictions.eq("name",name));

        WebUser user = (WebUser) criteria.uniqueResult();
        if ( user == null) {
           throw new UsernameNotFoundException("");
        }

        return user;
    }

    public void updateWebUser(WebUser webUser) {
        getSession().saveOrUpdate(webUser);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        WebUser webUser = findByName(username);

        List<GrantedAuthority> grantedAuthorities =
                webUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.toString()))
                        .collect(toList());

        return new User(webUser.getName(), webUser.getPassword(), grantedAuthorities);
    }
}
