package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService = new ProductService();

    @GetMapping("")
    public String index(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "/index";
    }
    @GetMapping("/create")
    public String edit(Model model) {
        model.addAttribute("product",new Product());
        return "/create";
    }
    @PostMapping("/save")
    public String save(Product product) {
        product.setId((int) (Math.random() * 10000));
        productService.save(product);
        return "redirect:/product";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/edit";
    }
    @PostMapping("/edit")
    public String update(Product product){
        productService.update(product.getId(), product);
        return "redirect:/product";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect){
        productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/product";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "/view";
    }
    @PostMapping("/search")
    public String view(@RequestParam String name, Model model){
        List<Product> products = productService.search(name);
        model.addAttribute("product", products);
        return "/index";
    }
}
