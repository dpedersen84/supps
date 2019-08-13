package com.dp.supps.entities;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    private int id;

    @NotBlank(message = "Name field cannot be blank!")
    @Size(max = 100, message = "User name cannot be more than 100 characters!")
    private String username;

    private boolean isAdmin;

    @NotBlank(message = "Password field cannot be blank!")
    @Size(max = 100, message = "Password cannot be more than 100 characters!")
    private String password;
    
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
