package com.codegym.province_management.repository;

import com.codegym.province_management.model.Customer;
import com.codegym.province_management.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);
    Page<Customer> findByFirstNameContaining(String firstName, Pageable pageable);
}
