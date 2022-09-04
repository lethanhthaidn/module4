package com.codegym.shoppingcart.service.Impl;

import com.codegym.shoppingcart.model.Product;
import com.codegym.shoppingcart.repository.ProductRepository;
import com.codegym.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }
}
