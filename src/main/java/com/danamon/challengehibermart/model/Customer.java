package com.danamon.challengehibermart.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="m_customer")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "membership_status")
    private int membershipStatus;

    public Customer(int id, String name, String address, String phone, Date birthdate, int membershipStatus) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birthdate = birthdate;
        this.membershipStatus = membershipStatus;
    }

    public Customer(String name, String address, String phone, Date birthdate, int membershipStatus) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birthdate = birthdate;
        this.membershipStatus = membershipStatus;
    }

    public Customer() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(int membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate=" + birthdate +
                ", membershipStatus=" + membershipStatus +
                '}';
    }
}