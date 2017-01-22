package jdbcAuthentication.foo.service;// Created by Mateusz Płuciennik on 02.01.17.

import jdbcAuthentication.conf.datasource.DataSourceConfig;
import jdbcAuthentication.conf.datasource.HibernateConfiguration;
import jdbcAuthentication.conf.service.ServiceConfiguration;
import jdbcAuthentication.foo.dao.WebUserDao;
import jdbcAuthentication.foo.model.WebUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, HibernateConfiguration.class, ServiceConfiguration.class})
public class WebUserServiceImplTest {

    @Mock
    private WebUserDao webUserDao;

    @InjectMocks
    private WebUserService webUserService = new WebUserServiceImpl();

    //just to gimme hope :)
    @Test
    public void rigorousTest() {
        assertTrue(true);
    }

    @Test
    public void shouldLoadUserByUsername() {
        //given
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        User expectedUser = new User("user", "user", grantedAuthorities);

        //when
        when(webUserDao.loadUserByUsername("user")).thenReturn(expectedUser);

        //then
        UserDetails user = webUserService.loadUserByUsername("user");

        Assert.assertTrue(user.equals(expectedUser));
        verify(webUserDao, times(1)).loadUserByUsername("user");

    }

    @Test
    public void shouldInvokeSaveUserFunction() {
        //given
        WebUser webUser = new WebUser("user", "user");

        //when
        webUserService.saveUser(webUser);

        //then
        verify(webUserDao, times(1)).saveUser(webUser);
    }

    @Test
    public void shouldInvokeShowAllWebUsersFunction() {
        //given
        List<WebUser> expectedWebUsersList = new ArrayList<>();
        expectedWebUsersList.add(new WebUser("admin", "admin"));
        expectedWebUsersList.add(new WebUser("user", "user"));

        //when
        when(webUserDao.showAllWebUsers()).thenReturn(expectedWebUsersList);

        //then
        List<WebUser> webUsersList =  webUserService.showAllWebUsers();

        Assert.assertTrue(webUsersList.equals(expectedWebUsersList));
        verify(webUserDao, times(1)).showAllWebUsers();
    }

    @Test
    public void shouldInvokeFindByNameFunction(){
        //given
        String username = "user";
        WebUser expectedWebUser = new WebUser(username, "password");

        //when
        when(webUserDao.findByName(username)).thenReturn(expectedWebUser);

        //then
        WebUser webUser = webUserService.findByName(username);

        verify(webUserDao, times(1)).findByName(username);
        Assert.assertTrue(webUser.equals(expectedWebUser));
    }

    @Test
    public void shouldInvokeUpdateWebUserFunction(){
        //given
        WebUser webUser = new WebUser("user", "password");

        //when
        webUserService.updateWebUser(webUser);

        //then
        verify(webUserDao, times(1)).updateWebUser(webUser);
    }

    @Test
    public void shouldInvokeDeleteWebUserByName() {
        //given
        String username = "user";

        //when
        webUserService.deleteWebUserByName(username);

        //then
        verify(webUserDao, times(1)).deleteWebUserByName(username);
    }




}
