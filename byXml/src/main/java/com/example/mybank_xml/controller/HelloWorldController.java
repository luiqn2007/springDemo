package com.example.mybank_xml.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldController {

    @RequestMapping("/")
    public ModelAndView handleRequest() {
        Map<String, String> modelData = new HashMap<>();
        modelData.put("message", "Hello World!");
        return new ModelAndView("hello", modelData);
    }
}
