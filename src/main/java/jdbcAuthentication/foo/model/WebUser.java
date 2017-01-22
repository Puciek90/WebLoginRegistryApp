package jdbcAuthentication.foo.model;// Created by Mateusz Płuciennik on 07.11.16.

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "webusers")
public class WebUser {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(orphanRemoval = true)//, fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "user_name")
    private List<Roles> roles;


    public WebUser() {
    }

    public WebUser(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User name = " + name + " password = " + password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebUser)) return false;
        WebUser webUser = (WebUser) o;
        return Objects.equals(getName(), webUser.getName()) &&
                Objects.equals(getPassword(), webUser.getPassword()) &&
                Objects.equals(getRoles(), webUser.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}

