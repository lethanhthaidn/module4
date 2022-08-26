package com.codegym.blog_management.service;

import com.codegym.blog_management.model.Blog;
import com.codegym.blog_management.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGeneralService<Blog>{
    Page<Blog> findAll(Pageable pageable);
    Iterable<Blog> findAllByCategory(Category category);
    Page<Blog> findByNameContaining(String name, Pageable pageable);
}
