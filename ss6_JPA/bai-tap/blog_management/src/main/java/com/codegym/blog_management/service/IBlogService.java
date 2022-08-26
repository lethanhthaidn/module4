package com.codegym.blog_management.service;

import com.codegym.blog_management.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGeneralService<Blog>{
    Page<Blog> findAll(Pageable pageable);
}
