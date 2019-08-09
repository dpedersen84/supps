package com.dp.supps.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Category {

    private int id;

    @NotBlank(message = "Category name cannot be blank!")
    @Size(max = 25, message = "Category name cannot be more than 25 characters!")
    private String name;

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

}
