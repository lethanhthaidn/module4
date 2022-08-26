package com.codegym.blog_management.service;

import com.codegym.blog_management.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService extends IGeneralService<Category>{
    Page<Category> findAll(Pageable pageable);
}
