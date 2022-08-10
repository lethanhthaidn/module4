package com.codegym.demospringboot.controller;

import com.codegym.demospringboot.model.Product;
import com.codegym.demospringboot.service.ProductService;
import com.codegym.demospringboot.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductService productServiceImpl;
    @GetMapping("/product")
    public String list(Model model){
        model.addAttribute("product", new Product());
        return "/list";
    }
}
