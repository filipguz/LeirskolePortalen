package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leir") // Alle ruter starter med /leir
public class LeirController {

    private final LeirRepository leirRepo;

    public LeirController(LeirRepository leirRepo) {
        this.leirRepo = leirRepo;
    }

    // ---------- CREATE ----------
    // Viser skjema for å opprette en ny leir
    @GetMapping("/ny")
    public String nyLeirForm(Model model) {
        model.addAttribute("leir", new Leir());
        return "leir/ny"; // Vis skjema (ny.html)
    }

    // Lagre ny leir fra skjema
    @PostMapping("/lagre")
    public String lagreLeir(@ModelAttribute Leir leir) {
        leirRepo.save(leir); // Lagre i database
        return "redirect:/leir/liste"; // Gå til oversikt
    }

    // ---------- READ ----------
    // Vis alle leirer
    @GetMapping("/liste")
    public String visAlle(Model model) {
        model.addAttribute("leirer", leirRepo.findAll());
        return "leir/liste";
    }

    // ---------- UPDATE ----------
    // Vise redigeringsskjema for en eksisterende leir
    @GetMapping("/rediger/{id}")
    public String redigerLeirForm(@PathVariable Long id, Model model) {
        Leir leir = leirRepo.findById(id).orElseThrow();
        model.addAttribute("leir", leir);
        return "leir/rediger"; // Viser redigeringsskjema
    }

    // Lagre endringer etter redigering
    @PostMapping("/oppdater")
    public String oppdaterLeir(@ModelAttribute Leir leir) {
        leirRepo.save(leir); // Lagre oppdatert leir
        return "redirect:/leir/liste";
    }

    // ---------- DELETE ----------
    // Slett en leir (via POST for sikkerhet)
    @PostMapping("/slett/{id}")
    public String slettLeir(@PathVariable Long id) {
        leirRepo.deleteById(id);
        return "redirect:/leir/liste";
    }
}
