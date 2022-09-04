package com.codegym.blog_management.controller;

import com.codegym.blog_management.model.Blog;
import com.codegym.blog_management.model.Category;
import com.codegym.blog_management.service.IBlogService;
import com.codegym.blog_management.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;
    @ModelAttribute("category")
    public Iterable<Category> category(){
        return categoryService.findAll();
    }
    @GetMapping("/blog")
    public ModelAndView listBlog(@RequestParam("search") Optional<String> search, @PageableDefault(5) Pageable pageable) {
        Page<Blog> blog;
        if (search.isPresent()) {
           blog = blogService.findAllByNameContaining(search.get(), pageable);
        }else {
            blog = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("category", categoryService.findAll());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public String saveBlog(@ModelAttribute("blog") Blog blog, RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Created Successfully");
        return "redirect:/create-blog";
    }

    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Optional<Blog> blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("blog/delete");
        modelAndView.addObject("blog", blog.get());
        return modelAndView;
    }

    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:blog";
    }

    @GetMapping("/view-blog/{id}")
    public ModelAndView viewBlog(@PathVariable Integer id) {
        Optional<Blog> blogs = blogService.findById(id);
        if (!blogs.isPresent()) {
            return new ModelAndView("blog/error");
        } else {
            ModelAndView modelAndView = new ModelAndView("blog/view");
            modelAndView.addObject("blog", blogs.get());
            modelAndView.addObject("customer", blogs);
            return modelAndView;
        }
    }
    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id){
        Optional<Blog> blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog", blog.get());
        return modelAndView;
    }
    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }
}
