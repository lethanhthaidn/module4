package com.codegym.blog_management.controller;

import com.codegym.blog_management.model.Blog;
import com.codegym.blog_management.model.Category;
import com.codegym.blog_management.service.BlogService;
import com.codegym.blog_management.service.IBlogService;
import com.codegym.blog_management.service.ICategoryService;
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
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/category")
    public ModelAndView listCategory(@PageableDefault(5) Pageable pageable) {
        Page<Category> category = categoryService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("category", category);
        return modelAndView;
    }
    @GetMapping("/create-category")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category" , new Category());
        return modelAndView;
    }
    @PostMapping("/create-category")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes redirect){
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Created Successfully");
        return "redirect:/create-category";
    }
    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id){
        Optional<Category> category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", category);
        return modelAndView;
    }
    @PostMapping("/edit-category")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "edit ok");
        return modelAndView;
    }
    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id){
        Optional<Category> category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("category/delete");
        modelAndView.addObject("category", category.get());
        return modelAndView;
    }
    @PostMapping("/delete-category")
    public String deleteCategory(@ModelAttribute("province") Category category){
        categoryService.remove(category.getId());
        return "redirect:category";
    }
    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Integer id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ModelAndView("/error.404");
        } else {

            Iterable<Blog> blog = blogService.findAllByCategory(categoryOptional.get());

            ModelAndView modelAndView = new ModelAndView("category/view");
            modelAndView.addObject("category", categoryOptional.get());
            modelAndView.addObject("blog", blog);
            return modelAndView;
        }
    }
}

