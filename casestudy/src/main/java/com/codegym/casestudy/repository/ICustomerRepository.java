package com.codegym.casestudy.repository;

import com.codegym.casestudy.model.Customer;
import com.codegym.casestudy.model.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    Iterable<Customer> findAllByCustomerType(CustomerType customerType);
}
