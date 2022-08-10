package com.codegym.product_management.controller;

import com.codegym.product_management.model.Product;
import com.codegym.product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/product")
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("product", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "Created successfully");
        return modelAndView;
    }

    @GetMapping("edit/{id}")
    public ModelAndView showEditForm(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        }else {
            return new ModelAndView ("/error.404");
        }
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "updated successfully");
        return modelAndView;
    }
    @GetMapping("delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id){
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("product" , product.get());
            return modelAndView;
        }else {return new ModelAndView("/error.404");}
    }
    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.remove(product.getId());
        return "redirect:/product";
    }
}
