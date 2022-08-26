package com.codegym.province_management.repository;

import com.codegym.province_management.model.Customer;
import com.codegym.province_management.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends PagingAndSortingRepository<Province, Long> {
//    Iterable<Province> findAllByCustomer(Customer customer);
    Page<Province> findAll(Pageable pageable);
}
