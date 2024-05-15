package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="category")
public class Category {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name="category_id")
    private int id;

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

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }
}
