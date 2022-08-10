package com.codegym.demospringboot.repository;

import com.codegym.demospringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Integer>{
    List<Product> findAll();

}
