package com.codegym.form_validation.controller;

import com.codegym.form_validation.dto.UserDto;
import com.codegym.form_validation.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @GetMapping("/form")
    public String showForm(Model model){
        model.addAttribute ("userDto", new UserDto());
        return "index";
    }
    @PostMapping("/form")
    public String checkForm(@Valid @ModelAttribute("userDto") UserDto userDto,
                            BindingResult bindingResult, Model model){
        if (bindingResult.hasFieldErrors()){
            return "/index";
        }
        else {
            model.addAttribute("userDto", userDto);
            return "/result";
        }
    }
}
