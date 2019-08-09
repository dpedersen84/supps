package com.dp.supps.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    private int id;

    @NotBlank(message = "Name field cannot be blank!")
    @Size(max = 100, message = "User name cannot be more than 100 characters!")
    private String name;

    @NotBlank(message = "Email field cannot be blank!")
    @Size(max = 50, message = "Email cannot be more than 50 characters!")
    private String email;

    private boolean isAdmin;

    @NotBlank(message = "Password field cannot be blank!")
    @Size(max = 100, message = "Password cannot be more than 100 characters!")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
