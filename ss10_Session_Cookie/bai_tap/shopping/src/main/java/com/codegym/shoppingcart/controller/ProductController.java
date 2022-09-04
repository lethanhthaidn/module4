package com.codegym.shoppingcart.controller;

import com.codegym.shoppingcart.model.Cart;
import com.codegym.shoppingcart.model.Product;
import com.codegym.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("/shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error";
        }
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shop";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }

    @GetMapping("/delete/{id}")
    public String deleteToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent())  {
            return "/error";
        }
        if (action.equals("show")) {
            cart.deleteProduct(productOptional.get());
            return "redirect:/shop";
        }
        cart.deleteProduct(productOptional.get());
        return "redirect:/shop";
    }

    @GetMapping("/info/{id}")
    public ModelAndView getInfo(@PathVariable Long id) {
        return new ModelAndView("info", "products", productService.getById(id));
    }
}
