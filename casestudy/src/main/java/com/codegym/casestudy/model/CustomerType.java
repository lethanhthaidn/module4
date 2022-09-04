package com.codegym.casestudy.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class CustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customersTypeId;
    private String CustomerTypeName;

    @OneToMany(mappedBy = "customerType")
    private Set<Customer> customer;

    public Set<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(Set<Customer> customer) {
        this.customer = customer;
    }

    public CustomerType() {
    }



    public int getCustomersTypeId() {
        return customersTypeId;
    }

    public void setCustomersTypeId(int customersTypeId) {
        this.customersTypeId = customersTypeId;
    }

    public String getCustomerTypeName() {
        return CustomerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        CustomerTypeName = customerTypeName;
    }
}
