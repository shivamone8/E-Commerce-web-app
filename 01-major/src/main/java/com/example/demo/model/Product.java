package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")

    private Category category;
    //we can't handle this object of category directly at front end and that's why we use DTO(data transfer Object) for that
    private double price;
    private double weight;
    private String description;
    private String imageName;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + product_id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
