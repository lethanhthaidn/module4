package com.codegym.blog_management.repository;

import com.codegym.blog_management.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends PagingAndSortingRepository<Category, Integer>{
    Page<Category> findAll(Pageable pageable);
}
