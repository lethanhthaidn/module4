package com.codegym.province_management.controller;

import com.codegym.province_management.model.Customer;
import com.codegym.province_management.model.Province;
import com.codegym.province_management.service.ICustomerService;
import com.codegym.province_management.service.IProvinceService;
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
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;
    @ModelAttribute("province")
    public Iterable<Province> provinces(){return provinceService.findAll();}

    @GetMapping("/customer")
    public ModelAndView listCustomers(@RequestParam("search") Optional<String> search,@PageableDefault(5) Pageable pageable) {
        Page<Customer> customer;
        if (search.isPresent()){
            customer = customerService.findByFirstNameContaining(search.get(), pageable);
        }else{
            customer = customerService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @GetMapping("/create-customer")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("province",provinceService.findAll());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirect) {
        customerService.save(customer);
        redirect.addFlashAttribute("message", "New customer created successfully");
        return "redirect:/create-customer";
    }
    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
            ModelAndView modelAndView = new ModelAndView("customer/delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;

    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:customer";
    }
}
