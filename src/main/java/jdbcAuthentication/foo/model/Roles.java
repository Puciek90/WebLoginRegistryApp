package jdbcAuthentication.foo.model;// Created by Mateusz Płuciennik on 08.11.16.

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Roles {

    public Roles() {};

    public Roles(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column (name = "id")
    private int id;

    @Column(name = "role")
    private String role;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roles)) return false;
        Roles roles = (Roles) o;
        return Objects.equals(getRole(), roles.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole());
    }
}
