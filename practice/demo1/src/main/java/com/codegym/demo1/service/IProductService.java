package com.codegym.demo1.service;

import com.codegym.demo1.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageInfo);
    Page<Product> search(String keyword, Pageable pageInfo);

    Optional<Product> findOne(Long id);

    Product save(Product product);
}
