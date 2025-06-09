// src/main/java/com/example/LeirskolePortalen/controller/AuthController.java
package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.User;
import com.example.LeirskolePortalen.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {
        if (userService.login(email, password)) {
            session.setAttribute("user", email);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Feil e-post eller passord");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam String email,
                                 @RequestParam String password,
                                 Model model) {
        if (userService.register(new User(email, password))) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Bruker finnes allerede");
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
