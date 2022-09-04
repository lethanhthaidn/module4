package com.codegym.casestudy.service;

import com.codegym.casestudy.model.Customer;
import com.codegym.casestudy.model.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAll();

    Optional<Customer> findById(int id);

    void save(Customer customer);

    void remove(int id);

    Iterable<Customer> findAllByCustomerType(CustomerType customerType);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findByNameContaining(String customerName, Pageable pageable);
}
