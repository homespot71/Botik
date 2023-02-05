package Botik.model;

import Botik.configuration.RoleConfiguration;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Enumerated(EnumType.STRING)
    private RoleConfiguration userRole;

    public User() {

    }

    public User(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RoleConfiguration getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleConfiguration userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
