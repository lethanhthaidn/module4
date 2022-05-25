package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class DictionaryController {
    private static Map<String,String> dictionary;

    static {
        dictionary = new LinkedHashMap<>();
        dictionary.put("hello","xin chao");
        dictionary.put("teacher","giao vien");
        dictionary.put("student","sinh vien");
        dictionary.put("learn","hoc");
        dictionary.put("computer","may tinh");
    }
    @GetMapping("/")
    public String view(){
        return "index";
    }
    @PostMapping("/translate")
    public String translate (@RequestParam() String input , Model model){
        if (dictionary.get(input)!=null) {
            model.addAttribute("vietnamese",dictionary.get(input));
        }
        else{
            model.addAttribute("input", "cannot found");
        }
        model.addAttribute("input",input);
        return "result";
    }
}
