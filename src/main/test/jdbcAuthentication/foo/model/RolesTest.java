package jdbcAuthentication.foo.model;// Created by Mateusz Płuciennik on 02.01.17.

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RolesTest {

    @Test
    public void shouldBeEqual() {
        //given
        List<Roles> roles = new ArrayList<>();
        List<Roles> roles2 = new ArrayList<>();

        roles.add(new Roles("USER_ROLE"));
        roles2.add(new Roles("USER_ROLE"));

        roles.add(new Roles("ADMIN_ROLE"));
        roles2.add(new Roles("ADMIN_ROLE"));

        //then
        Assert.assertTrue(roles.equals(roles2));

    }
}
