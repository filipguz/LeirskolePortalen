package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/leir")
public class LeirController {

    private final LeirRepository leirRepo;

    public LeirController(LeirRepository leirRepo) {
        this.leirRepo = leirRepo;
    }

    // ---------- CREATE ----------
    @GetMapping("/ny")
    public String nyLeirForm(Model model) {
        model.addAttribute("leir", new Leir());
        return "leir/ny";
    }

    @PostMapping("/lagre")
    public String lagreLeir(@ModelAttribute Leir leir, RedirectAttributes ra) {
        leirRepo.save(leir);
        ra.addFlashAttribute("melding", "Leiren ble opprettet.");
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
    public String redigerLeirForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Optional<Leir> leirOpt = leirRepo.findById(id);
        if (leirOpt.isPresent()) {
            model.addAttribute("leir", leirOpt.get());
            return "leir/rediger";
        } else {
            ra.addFlashAttribute("feil", "Leir med ID " + id + " ble ikke funnet.");
            return "redirect:/leir/liste";
        }
    }

    @PostMapping("/oppdater")
    public String oppdaterLeir(@ModelAttribute Leir leir, RedirectAttributes ra) {
        leirRepo.save(leir);
        ra.addFlashAttribute("melding", "Leiren ble oppdatert.");
        return "redirect:/leir/liste";
    }

    // ---------- DELETE ----------
    @PostMapping("/slett/{id}")
    public String slettLeir(@PathVariable Long id, RedirectAttributes ra) {
        if (leirRepo.existsById(id)) {
            leirRepo.deleteById(id);
            ra.addFlashAttribute("melding", "Leiren ble slettet.");
        } else {
            ra.addFlashAttribute("feil", "Fant ikke leiren du prøvde å slette.");
        }
        return "redirect:/leir/liste";
    }
}
