package com.codegym.province_management.controller;

import com.codegym.province_management.model.Customer;
import com.codegym.province_management.model.Province;
import com.codegym.province_management.service.CustomerService;
import com.codegym.province_management.service.IGeneralService;
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

public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private CustomerService customerService;
    @GetMapping("province")
    public ModelAndView listProvince(@PageableDefault(2)Pageable pageable){
        Page<Province> province = provinceService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("province/list");
        modelAndView.addObject("province", province);
        return modelAndView;
    }
    @GetMapping("/create-province")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province" , new Province());
        return modelAndView;
    }
    @PostMapping("/create-province")
    public String saveProvince(@ModelAttribute("province") Province province, RedirectAttributes redirect){
        provinceService.save(province);
        redirect.addFlashAttribute("message", "Created Successfully");
        return "redirect:/create-province";
    }
    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Province> province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("province/edit");
        modelAndView.addObject("province", province);
        return modelAndView;
    }
    @PostMapping("/edit-province")
    public ModelAndView updateProvince(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("province/edit");
        modelAndView.addObject("province", province);
        modelAndView.addObject("message", "edit ok");
        return modelAndView;
    }
    @GetMapping("/delete-province/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Optional<Province> province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("province/delete");
        modelAndView.addObject("province", province.get());
        return modelAndView;
    }
    @PostMapping("/delete-province")
    public String deleteProvince(@ModelAttribute("province") Province province){
        provinceService.remove(province.getId());
        return "redirect:province";
    }
    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id) {
        Optional<Province> provinceOptional = provinceService.findById(id);
        if (!provinceOptional.isPresent()) {
            return new ModelAndView("/error.404");
        } else {

            Iterable<Customer> customer = customerService.findAllByProvinces(provinceOptional.get());

            ModelAndView modelAndView = new ModelAndView("province/view");
            modelAndView.addObject("province", provinceOptional.get());
            modelAndView.addObject("customer", customer);
            return modelAndView;
        }
    }
}
