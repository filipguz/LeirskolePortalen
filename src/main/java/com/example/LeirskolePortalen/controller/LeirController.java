package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.model.Skole;
import com.example.LeirskolePortalen.repository.LeirRepository;
import com.example.LeirskolePortalen.repository.SkoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/leir")
public class LeirController {

    private final LeirRepository leirRepo;
    private final SkoleRepository skoleRepo;

    public LeirController(LeirRepository leirRepo, SkoleRepository skoleRepo) {
        this.leirRepo = leirRepo;
        this.skoleRepo = skoleRepo;
    }

    // ---------- CREATE ----------
    @GetMapping("/ny")
    public String nyLeirForm(Model model) {
        model.addAttribute("leir", new Leir());
        model.addAttribute("skoler", skoleRepo.findAll()); // Vis liste med skoler
        return "leir/ny";
    }

    @PostMapping("/lagre")
    public String lagreLeir(@RequestParam List<Long> skoler, @ModelAttribute Leir leir) {
        List<Skole> skoleList = skoleRepo.findAllById(skoler);
        leir.setSkoler(skoleList);

        // evt sett antall elever
        if (leir.getAntallJenter() != null && leir.getAntallGutter() != null) {
            leir.setAntallElever(leir.getAntallJenter() + leir.getAntallGutter());
        }
        leirRepo.save(leir);
        return "redirect:/leir/liste";
    }

    // ---------- READ ----------
    @GetMapping("/liste")
    public String visAlle(Model model) {
        model.addAttribute("leirer", leirRepo.findAll());
        return "leir/liste";
    }

    // ---------- UPDATE ----------
    @GetMapping("/rediger/{id}")
    public String redigerLeirForm(@PathVariable Long id, Model model) {
        Leir leir = leirRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Leir ikke funnet med ID: " + id));
        model.addAttribute("leir", leir);
        model.addAttribute("skoler", skoleRepo.findAll()); // Valg av skole ved redigering
        return "leir/rediger";
    }

    @PostMapping("/oppdater")
    public String oppdaterLeir(@ModelAttribute Leir leir) {
        if (leir.getAntallJenter() != null && leir.getAntallGutter() != null) {
            leir.setAntallElever(leir.getAntallJenter() + leir.getAntallGutter());
        }
        leirRepo.save(leir);
        return "redirect:/leir/liste";
    }

    // ---------- DELETE ----------
    @PostMapping("/slett/{id}")
    public String slettLeir(@PathVariable Long id) {
        leirRepo.deleteById(id);
        return "redirect:/leir/liste";
    }
}






/*
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
        leirRepo.save(leir);
        return "redirect:/leir/liste";
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

 */

