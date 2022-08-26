package com.codegym.blog_management.controller;

import com.codegym.blog_management.model.Blog;
import com.codegym.blog_management.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("/blog")
    public ModelAndView listBlog(@PageableDefault(10) Pageable pageable) {
        Page<Blog> blog;
        blog = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("blog", new Blog());
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
        ModelAndView modelAndView = new ModelAndView("/delete");
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
            return new ModelAndView("/error");
        } else {
            ModelAndView modelAndView = new ModelAndView("/view");
            modelAndView.addObject("blog", blogs.get());
            modelAndView.addObject("customer", blogs);
            return modelAndView;
        }
    }
    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id){
        Optional<Blog> blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }
}
