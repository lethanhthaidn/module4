package com.codegym.demospringboot.service;

import com.codegym.demospringboot.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService  {
    List<Product> findAll();
    void create(Product product);
    void save(Product product);
    void delete(Product product);
    void update(Product product);
    List<Product> findById(int id);
}
