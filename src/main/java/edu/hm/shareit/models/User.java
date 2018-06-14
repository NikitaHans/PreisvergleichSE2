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
    private String status;
    private boolean admin;

    public User(){}

    public User(String mail, String password, String status, boolean admin){
        this.mail = mail;
        this.password = password;
        this.status = status;
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

    public String getStatus() {
        return status;
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
                Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, password, status, admin);
    }
}
