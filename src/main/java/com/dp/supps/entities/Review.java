package com.dp.supps.entities;

import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Review {

    private int id;

    @Min(1)
    @Max(5)
    private int rating;

    private int productId;

    @Size(max = 300, message = "No more than 300 characters!")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
        hash = 23 * hash + this.rating;
        hash = 23 * hash + this.productId;
        hash = 23 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Review other = (Review) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.rating != other.rating) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
}