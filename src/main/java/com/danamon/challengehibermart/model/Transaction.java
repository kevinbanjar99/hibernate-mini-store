package com.danamon.challengehibermart.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="t_transaction")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "total_quantity")
    private int totalQuantity;
    @Column(name = "total_price")
    private int totalPrice;

    public Transaction(int id, Date date, Customer customer, int totalQuantity, int totalPrice) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public Transaction(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
        this.totalQuantity = 0;
        this.totalPrice = 0;
    }

    public Transaction(Customer customer) {
        this.date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());;
        this.customer = customer;
        this.totalQuantity = 0;
        this.totalPrice = 0;
    }


    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
