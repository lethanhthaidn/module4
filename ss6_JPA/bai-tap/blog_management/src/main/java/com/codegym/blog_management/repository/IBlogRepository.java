package com.codegym.blog_management.repository;

import com.codegym.blog_management.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends PagingAndSortingRepository<Blog, Integer> {
    Page<Blog> findAll(Pageable pageable);
}
