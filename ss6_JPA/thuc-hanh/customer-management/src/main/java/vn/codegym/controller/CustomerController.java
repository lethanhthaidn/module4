package vn.codegym.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.model.Customer;
import vn.codegym.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerServiceImpl;

    @GetMapping("")
    public ModelAndView list(){
        List<Customer> customersList = customerServiceImpl.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("customers", customersList);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute("customer") Customer customer) {
        customerServiceImpl.save(customer);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "Created Cuccessfully!");
        return modelAndView;
    }
}
