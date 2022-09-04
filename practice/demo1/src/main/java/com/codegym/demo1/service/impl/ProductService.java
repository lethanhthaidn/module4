package com.codegym.demo1.service.impl;

import com.codegym.demo1.model.Product;
import com.codegym.demo1.repository.IProductRepository;
import com.codegym.demo1.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

//    private Stream<Product> streamAll() {
//        return StreamSupport.stream(productRepository.findAll().spliterator(), false);
//    }
    @Override
    public Page<Product> findAll(Pageable pageInfo) {
        return productRepository.findAll(pageInfo);
    }

    @Override
    public Page<Product> search(String keyword, Pageable pageInfo) {
        Iterable<Product> searchResult = productRepository
                .findAllByNameContainsOrManufacturerContains(keyword, keyword);
        return streamAll(searchResult).collect(Collectors.toList());
    }

    private Stream<Product> streamAll(Iterable<Product> product) {
        return StreamSupport.stream(product.spliterator(), false);
    }

    @Override
    public Optional<Product> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        return null;
    }
}
