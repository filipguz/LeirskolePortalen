package com.example.LeirskolePortalen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // Returnerer view-navnet "login", altså login.html i templates-mappen
        return "login";
    }
}
