package com.codegym.customer_management.repository;

import com.codegym.customer_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
