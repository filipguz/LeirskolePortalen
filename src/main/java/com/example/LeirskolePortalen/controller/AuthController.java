// src/main/java/com/example/LeirskolePortalen/controller/AuthController.java
package com.example.LeirskolePortalen.controller;


import com.example.LeirskolePortalen.model.Bruker;
import com.example.LeirskolePortalen.repository.BrukerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private BrukerRepository brukerRepo;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/register")
    public String visRegistrering(Model model) {
        model.addAttribute("bruker", new Bruker());
        return "register";
    }

    @PostMapping("/register")
    public String lagreBruker(@ModelAttribute Bruker bruker) {
        bruker.setPassord(encoder.encode(bruker.getPassord()));
        brukerRepo.save(bruker);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String visLogin() {
        return "login";
    }
}
