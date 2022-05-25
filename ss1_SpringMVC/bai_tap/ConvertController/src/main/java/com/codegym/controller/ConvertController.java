package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ConvertController {
    @GetMapping("/")
    public String view() {
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam() double rate, @RequestParam() double usd, Model model) {
        double vnd = rate * usd;
        model.addAttribute("rate", rate);
        model.addAttribute("usd", usd);
        model.addAttribute("vnd", vnd);
     return "index";
    }
}

