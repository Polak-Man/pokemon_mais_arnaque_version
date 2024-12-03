// src/main/java/org/example/models/User.java

package org.example.models;

import java.util.Objects;

public class User {

    private String pseudo;
    private String pwd;
    private boolean isAdmin;

    // Constructor
    public User(String pseudo, String pwd, boolean isAdmin) {
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.isAdmin = isAdmin;
    }

    // Getters
    public String getPseudo() {
        return pseudo;
    }

    public String getPwd() {
        return pwd;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    // Setters
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    // Utility Methods
    @Override
    public String toString() {
        return "User{" +
                "pseudo='" + pseudo + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin &&
                Objects.equals(pseudo, user.pseudo) &&
                Objects.equals(pwd, user.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo, pwd, isAdmin);
    }
}
