package com.codegym.province_management.service;

import com.codegym.province_management.model.Customer;
import com.codegym.province_management.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGeneralService<Customer> {
    Iterable<Customer> findAllByProvinces(Province province);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findByFirstNameContaining(String firstName, Pageable pageable);
}
