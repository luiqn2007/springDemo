package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

    @RequestMapping("/")
    public String root() {
        return "redirect:/thymeleaf/";
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf() {
        return "redirect:/thymeleaf/";
    }
}
