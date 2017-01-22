package jdbcAuthentication.foo.dao;// Created by Mateusz Płuciennik on 26.10.16.

import jdbcAuthentication.conf.datasource.DataSourceConfig;
import jdbcAuthentication.conf.datasource.HibernateConfiguration;
import jdbcAuthentication.conf.service.ServiceConfiguration;
import jdbcAuthentication.foo.model.Roles;
import jdbcAuthentication.foo.model.WebUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, HibernateConfiguration.class, ServiceConfiguration.class, WebWebUserDaoImpl.class})
public class WebUserDAOImplTest {

    @Autowired
    WebUserDao webUserDao;

    @Autowired
    DataSource dataSource;

     Connection connection;
     Statement statement;

    @Before
    public void initConnextionToDatabase() throws SQLException {
        connection = dataSource.getConnection();
        statement = connection.createStatement();
    }

    //just to gimme hope :)
    @Test
    public void rigorousTest() {
        assertTrue(true);
    }

    @Test
    @Transactional
    public void shouldGetUserFromDatabase() throws SQLException {
        statement.execute("INSERT INTO webusers VALUES ('jan', 'jurec')");
        statement.execute("INSERT INTO roles VALUES (3, 'jan', 'ROLE_USER')");


        //given
        String username = "marek";
        List<Roles> userRoles = new ArrayList<>();
        userRoles.add(new Roles("ROLE_USER"));


        //when
        WebUser webUser = webUserDao.findByName(username);


        //then
        Assert.assertNotNull(webUser);
        Assert.assertEquals("marek",webUser.getName());
        Assert.assertEquals("kwiatek",webUser.getPassword());
        Assert.assertFalse(webUser.getRoles().isEmpty());


        Assert.assertTrue(webUser.getRoles().size() == userRoles.size());
        Assert.assertTrue(webUser.getRoles().contains(new Roles("ROLE_USER")));
        Assert.assertTrue(userRoles.equals(webUser.getRoles()));

    }



    @Test (expected = UsernameNotFoundException.class)
    @Transactional
    public void shouldThrowUsernameNotFoundException() {
        //given
        String username = "noExistingOne";

        //when
        UserDetails user = webUserDao.loadUserByUsername(username);
    }

    @Test (expected = UsernameNotFoundException.class)
    @Transactional
    public void shouldThrowUsernameNotFoundException2() {
        //given
        String username = "noExistingOne";

        //when
        WebUser user = webUserDao.findByName(username);
    }

    @Test
    @Transactional
    public void shouldGetUsersList() throws SQLException {
        statement.execute("INSERT INTO roles VALUES (1, 'admin', 'ROLE_ADMIN')");
        statement.execute("INSERT INTO webusers VALUES ('admin', 'admin')");
        statement.execute("INSERT INTO roles VALUES (2, 'marek', 'ROLE_USER')");
        statement.execute("INSERT INTO webusers VALUES ('marek', 'kwiatek')");

        //given
        List<WebUser> databaseWebUserList = new ArrayList<>();

        List<Roles> userRole = new ArrayList<>();
        userRole.add(new Roles("ROLE_USER"));

        List<Roles> adminRole = new ArrayList<>();
        adminRole.add(new Roles("ROLE_ADMIN"));

        WebUser marek = new WebUser("marek", "kwiatek");
        marek.setRoles(userRole);

        WebUser admin = new WebUser("admin", "admin");
        admin.setRoles(adminRole);

        databaseWebUserList.add(admin);
        databaseWebUserList.add(marek);

        //when
        List<WebUser> webUsersList = webUserDao.showAllWebUsers();

        //then
        Assert.assertNotNull(webUsersList);
        assertThat(webUsersList, hasSize(2));
        Assert.assertTrue(databaseWebUserList.equals(webUsersList));

        // TODO: 15.01.17 Dalej porownywanie list
//        Assert.assertTrue(webUsersList.equals(databaseWebUserList));
    }

    @After
    public void closeConnectionToDatabase() throws SQLException {
        connection.close();
    }

    }
