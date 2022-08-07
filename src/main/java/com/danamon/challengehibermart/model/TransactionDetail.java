package com.danamon.challengehibermart.model;

import javax.persistence.*;

@Entity
@Table(name="t_transaction_detail")
public class TransactionDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "current_product_price")
    private int currentProductPrice;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "subtotal")
    private int subtotal;

    public TransactionDetail(int id, Transaction transaction, Product product, int currentProductPrice, int quantity, int subtotal) {
        this.id = id;
        this.transaction = transaction;
        this.product = product;
        this.currentProductPrice = currentProductPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public TransactionDetail(Transaction transaction, Product product, int currentProductPrice, int quantity, int subtotal) {
        this.transaction = transaction;
        this.product = product;
        this.currentProductPrice = currentProductPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public TransactionDetail(Transaction transaction, Product product, int quantity) {
        this.transaction = transaction;
        this.product = product;
        this.currentProductPrice = 0;
        this.quantity = quantity;
        this.subtotal = 0;
    }

    public TransactionDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCurrentProductPrice() {
        return currentProductPrice;
    }

    public void setCurrentProductPrice(int currentProductPrice) {
        this.currentProductPrice = currentProductPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", product=" + product +
                ", currentProductPrice=" + currentProductPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
