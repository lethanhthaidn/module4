package com.codegym.province_management.service;

import com.codegym.province_management.model.Customer;
import com.codegym.province_management.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProvinceService extends IGeneralService<Province>{
//    Iterable<Province> findAllByCustomer(Customer customer);
    Page<Province> findAll(Pageable pageable);
}
