package ru.alexander.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.alexander.NauJava.entity.CustomUserDetails;
import ru.alexander.NauJava.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String adduser(CustomUserDetails userDetails, Model model) {
        try {
            userService.addUser(userDetails);
            return "redirect:/login";
        }
        catch (Exception ex) {
            model.addAttribute("message", ex.getMessage());
            return "registration";
        }
    }
}
