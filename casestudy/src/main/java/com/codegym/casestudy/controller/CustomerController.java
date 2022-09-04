package com.codegym.casestudy.controller;

import com.codegym.casestudy.model.Customer;
import com.codegym.casestudy.model.CustomerType;
import com.codegym.casestudy.service.ICustomerService;
import com.codegym.casestudy.service.ICustomerTypeService;
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
    private ICustomerTypeService customerTypeService;
    @ModelAttribute("customerType")
    public Iterable<CustomerType> customerTypes(){return customerTypeService.findAll();}

    @GetMapping("/customer")
    public ModelAndView showListPage(@RequestParam("search") Optional<String> search, @PageableDefault(20) Pageable pageable){
        Page<Customer> customer;
        if (search.isPresent()){
            customer = customerService.findByNameContaining(search.get(),pageable);
        }else {
            customer = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @GetMapping("create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("customerTypes",customerTypeService.findAll());
        return modelAndView;
    }
    @PostMapping("/create-customer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes){
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Created Successfully!");
        return "redirect:customer";
    }
    @GetMapping("/edit-customer/{id}")
    public ModelAndView editCustomer(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView("customer/create");
        Optional<Customer> customer = customerService.findById(id);
        modelAndView.addObject("customer", customer.get());
        modelAndView.addObject("customerTypes",customerTypeService.findAll());
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


    @GetMapping("/delete-customer/{customerId}")
    public ModelAndView showDeleteForm(@PathVariable("customerId") Integer customerId){
        Optional<Customer> customer = customerService.findById(customerId);
        ModelAndView modelAndView = new ModelAndView("customer/delete");
        modelAndView.addObject("customer", customer.get());
        return modelAndView;
    }
    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getCustomerId());
        return "redirect:customer";
    }
}
