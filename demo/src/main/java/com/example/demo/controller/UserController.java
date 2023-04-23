package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getRegistrationForm(User user){
        return "/registration";
    }

    @PostMapping("/registration")
    public String registration(User user){
       userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal, Model model){
        User user = userService.findUserByName(principal.getName());
        System.out.println(principal.getName());
        model.addAttribute("user", user);
        return "/user";

    }
}
