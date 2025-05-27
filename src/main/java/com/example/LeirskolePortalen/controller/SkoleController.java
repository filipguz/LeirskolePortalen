package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Skole;
import com.example.LeirskolePortalen.repository.SkoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/skole") // Alle URL-er under /skole håndteres her
public class SkoleController {

    private final SkoleRepository skoleRepo;

    public SkoleController(SkoleRepository skoleRepo) {
        this.skoleRepo = skoleRepo;
    }

    // ===============================
    // ----------- CREATE -----------
    // ===============================

    // Viser skjema for å opprette en ny skole
    @GetMapping("/ny")
    public String nySkole(Model model) {
        model.addAttribute("skole", new Skole()); // Tomt objekt til skjema
        return "skole/skjema"; // Viser skole/skjema.html
    }

    // Lagrer ny eller oppdatert skole i databasen
    @PostMapping("/lagre")
    public String lagre(@ModelAttribute Skole skole) {
        skoleRepo.save(skole); // Hvis ID finnes, oppdateres, ellers opprettes ny
        return "redirect:/skole/kunder"; // Tilbake til listevisning
    }

    // ===============================
    // ------------ READ ------------
    // ===============================

    // Viser en oversikt over alle registrerte skoler
    @GetMapping("/kunder")
    public String alleSkoler(Model model) {
        model.addAttribute("skoler", skoleRepo.findAll()); // Henter alle fra DB
        return "skole/liste"; // Viser skole/liste.html
    }

    // ===============================
    // ----------- UPDATE -----------
    // ===============================

    // Henter én skole for redigering, basert på ID
    @GetMapping("/rediger/{id}")
    public String rediger(@PathVariable Long id, Model model) {
        Optional<Skole> skole = skoleRepo.findById(id);
        if (skole.isPresent()) {
            model.addAttribute("skole", skole.get()); // Fyller skjema med data
            return "skole/skjema";
        } else {
            return "redirect:/skole/kunder"; // Hvis ID ikke finnes
        }
    }

    // ===============================
    // ----------- DELETE -----------
    // ===============================

    // Sletter en skole basert på ID
    @GetMapping("/slett/{id}")
    public String slett(@PathVariable Long id) {
        if (skoleRepo.existsById(id)) {
            skoleRepo.deleteById(id);
        }
        return "redirect:/skole/kunder"; // Alltid tilbake til oversikten
    }
}
