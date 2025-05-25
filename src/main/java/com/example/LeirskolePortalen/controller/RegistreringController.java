/*package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Bruker;
import com.example.LeirskolePortalen.model.Rolle;
import com.example.LeirskolePortalen.repository.BrukerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistreringController {

    private final BrukerRepository repo;
    private final PasswordEncoder encoder;

    public RegistreringController(BrukerRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String visForm(Model model) {
        model.addAttribute("bruker", new Bruker());
        return "register";
    }

    @PostMapping("/register")
    public String registrerBruker(@ModelAttribute Bruker bruker) {
        bruker.setPassord(encoder.encode(bruker.getPassord()));
        bruker.setRolle(Rolle.TEACHER); // Sett fra skjema hvis ønsket
        repo.save(bruker);
        return "redirect:/login"; // Må stemme med LoginController
    }

}

 */
