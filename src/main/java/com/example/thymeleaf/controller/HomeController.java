package com.example.thymeleaf.controller;

import com.example.thymeleaf.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Optional;

@Controller
@RequestMapping("/thymeleaf")
public class HomeController {

    @RequestMapping("/")
    public String home(Model model, @Param("subscribe")Optional<String> subscribe) {
        model.addAttribute("today", Calendar.getInstance());
        subscribe.ifPresent(s -> model.addAttribute("subscribe", s));
        return "themeleaf/home";
    }

    @RequestMapping(value = "subscribe", method = RequestMethod.GET)
    public String subscribe() {
        return "themeleaf/subscribe";
    }

    @RequestMapping(value = "subscribe", method = RequestMethod.POST)
    public String subscribeAction(@Param("email") String email) {
        return "redirect:/thymeleaf/?subscribe=" + email;
    }

    @RequestMapping(value = "userprofile")
    public String userProfile(HttpSession session) {
        session.setAttribute("user", new User("John", "Doe", "Unknown", 99));
        return "themeleaf/userprofile";
    }
}
