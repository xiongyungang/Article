package com.xyg.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category implements Serializable{
    private Integer categoryId;
    private String categoryName;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Id
    @GeneratedValue
    public Integer getCategoryId() {
        return categoryId;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
