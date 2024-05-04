package com.example.mybank_xml.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/fixedDeposit/list";
    }
}
