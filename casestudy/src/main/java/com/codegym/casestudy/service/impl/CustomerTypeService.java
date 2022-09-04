package com.codegym.casestudy.service.impl;

import com.codegym.casestudy.model.CustomerType;
import com.codegym.casestudy.repository.ICustomerTypeRepository;
import com.codegym.casestudy.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerTypeService implements ICustomerTypeService {
    @Autowired
    private ICustomerTypeRepository customerTypeRepository;
    @Override
    public Iterable<CustomerType> findAll() {
        return customerTypeRepository.findAll() ;
    }

    @Override
    public Optional<CustomerType> findById(int id) {
        return customerTypeRepository.findById(id);
    }

    @Override
    public void save(CustomerType customerType) {
        customerTypeRepository.save(customerType);
    }

    @Override
    public void remove(int id) {
        customerTypeRepository.deleteById(id);
    }

    @Override
    public Page<CustomerType> findAll(Pageable pageable) {
        return customerTypeRepository.findAll(pageable);
    }
}
