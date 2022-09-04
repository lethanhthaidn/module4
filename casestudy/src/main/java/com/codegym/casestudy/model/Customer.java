package com.codegym.casestudy.model;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String customerName;
    private String birthDate;
    private String address;
    private String email;


    @ManyToOne
    @JoinColumn(name = "customerTypeId",referencedColumnName = "customersTypeId")
    private CustomerType customerType;

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Customer() {
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int id) {
        this.customerId = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + customerId + '\'' +
                ", name='" + customerName + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
