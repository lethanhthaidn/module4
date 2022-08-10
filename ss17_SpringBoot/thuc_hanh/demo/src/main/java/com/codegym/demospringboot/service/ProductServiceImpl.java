package com.codegym.demospringboot.service;

import com.codegym.demospringboot.model.Product;
import com.codegym.demospringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void create(Product product) {

    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> findById(int id) {
        return null;
    }
}
