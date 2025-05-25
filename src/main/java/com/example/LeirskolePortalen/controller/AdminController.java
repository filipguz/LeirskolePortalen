package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Bruker;
import com.example.LeirskolePortalen.model.Rolle;
import com.example.LeirskolePortalen.repository.BrukerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BrukerRepository brukerRepo;

    public AdminController(BrukerRepository brukerRepo) {
        this.brukerRepo = brukerRepo;
    }

    @GetMapping("/brukere")
    public String visBrukere(Model model) {
        model.addAttribute("brukere", brukerRepo.findAll());
        return "admin/bruker-liste";
    }

    @PostMapping("/endre-rolle")
    public String endreRolle(@RequestParam Long id, @RequestParam String rolle) {
        Bruker b = brukerRepo.findById(id).orElseThrow();
        b.setRolle(Rolle.valueOf(rolle));
        brukerRepo.save(b);
        return "redirect:/admin/brukere";
    }

    @PostMapping("/slett")
    public String slettBruker(@RequestParam Long id) {
        brukerRepo.deleteById(id);
        return "redirect:/admin/brukere";
    }
}
