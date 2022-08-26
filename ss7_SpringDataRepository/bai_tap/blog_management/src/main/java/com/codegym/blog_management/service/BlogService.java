package com.codegym.blog_management.service;

import com.codegym.blog_management.model.Blog;
import com.codegym.blog_management.model.Category;
import com.codegym.blog_management.repository.IBlogRepository;
import com.codegym.blog_management.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Integer id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Integer id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Iterable<Blog> findAllByCategory(Category category) {
        return blogRepository.findAllByCategory(category);
    }

    @Override
    public Page<Blog> findByNameContaining(String name, Pageable pageable) {
        return blogRepository.findByNameContaining("name", pageable);
    }
}
