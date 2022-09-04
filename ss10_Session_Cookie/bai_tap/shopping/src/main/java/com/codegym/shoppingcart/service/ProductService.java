package com.codegym.shoppingcart.service;

import com.codegym.shoppingcart.model.Product;

import java.util.Optional;

public interface ProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
    Product getById(Long id);

}
