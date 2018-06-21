package edu.hm.shareit.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "TUser")
public class User {
    @Id
    private String mail;
    private String password;
    private String nation;
    private boolean admin;

    public User(){}

    public User(String mail, String password, String status, boolean admin){
        this.mail = mail;
        this.password = password;
        this.nation = status;
        this.admin = admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getNation() {
        return nation;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return admin == user.admin &&
                Objects.equals(mail, user.mail) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nation, user.nation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, password, nation, admin);
    }
}
