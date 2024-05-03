package com.example.thymeleaf.controller;

import com.example.thymeleaf.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Optional;

@Controller
@RequestMapping
public class HomeController {

    @RequestMapping("/")
    public String home(Model model, @Param("subscribe") Optional<String> subscribe) {
        model.addAttribute("today", Calendar.getInstance());
        subscribe.ifPresent(s -> model.addAttribute("subscribe", s));
        return "home";
    }

    @GetMapping("subscribe")
    public String subscribe() {
        return "subscribe";
    }

    @PostMapping("subscribe")
    public String subscribeAction(@Param("email") String email) {
        return "redirect:/?subscribe=" + email;
    }

    @GetMapping("userprofile")
    public String userProfile(HttpSession session) {
        session.setAttribute("user", new User("John", "Doe", "Unknown", 99));
        return "userprofile";
    }
}
