package com.danamon.challengehibermart.model;

import javax.persistence.*;

@Entity
@Table(name = "m_product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "product_price")
    private int productPrice;
    @Column(name = "stock")
    private int stock;

    public Product(int id, String name, int productPrice, int stock) {
        this.id = id;
        this.name = name;
        this.productPrice = productPrice;
        this.stock = stock;
    }

    public Product(String name, int productPrice, int stock) {
        this.name = name;
        this.productPrice = productPrice;
        this.stock = stock;
    }

    public Product() {
    }

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

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productPrice=" + productPrice +
                ", stock=" + stock +
                '}';
    }
}
