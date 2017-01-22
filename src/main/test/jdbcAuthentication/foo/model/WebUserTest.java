package jdbcAuthentication.foo.model;// Created by Mateusz Płuciennik on 02.01.17.

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WebUserTest {

    @Test
    public void shouldBeEqual() {
        //users
        WebUser marek = new WebUser("marek", "kwiatek");
        WebUser marek2 = new WebUser("marek", "kwiatek");

        //roles
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles("ROLE_USER"));

        List<Roles> roles2 = new ArrayList<>();
        roles2.add(new Roles("ROLE_USER"));

        marek.setRoles(roles);
        marek2.setRoles(roles);

        //then
        Assert.assertTrue(marek.equals(marek2));
        Assert.assertTrue(marek.getRoles().equals(roles2));
    }
}
