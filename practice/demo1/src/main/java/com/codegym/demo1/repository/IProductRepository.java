package com.codegym.demo1.repository;

import com.codegym.demo1.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByNameContainsOrManufacturerContains(String name, String manufacturer);
    Page<Product>findAllByNameContainsOrManufacturerContains(String name, String manufacturer,
                                                             Pageable pageable);
}
