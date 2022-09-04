package com.codegym.casestudy.service;

import com.codegym.casestudy.model.Customer;
import com.codegym.casestudy.model.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICustomerTypeService {
    Iterable<CustomerType> findAll();

    Optional<CustomerType> findById(int id);

    void save(CustomerType customerType);

    void remove(int id);

    Page<CustomerType> findAll(Pageable pageable);
}
