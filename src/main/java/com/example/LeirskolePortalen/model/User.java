// src/main/java/com/example/LeirskolePortalen/model/User.java
package com.example.LeirskolePortalen.model;

public class User {
    private String email;
    private String password;

    // Konstruktører
    public User() {}
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Gettere og settere
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
