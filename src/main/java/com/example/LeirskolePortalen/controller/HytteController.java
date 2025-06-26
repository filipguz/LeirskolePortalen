package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Hytte;
import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.HytteRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hytte")
public class HytteController {

    private final HytteRepository hytteRepo;
    private final LeirRepository leirRepo;

    public HytteController(HytteRepository hytteRepo, LeirRepository leirRepo) {
        this.hytteRepo = hytteRepo;
        this.leirRepo = leirRepo;
    }

    // ========================
    // VISE HYTTER FOR LEIR
    // ========================
    @GetMapping("/liste/{leirId}")
    public String visHytter(@PathVariable Long leirId, Model model) {
        List<Hytte> hytter = hytteRepo.findByLeirId(leirId);
        model.addAttribute("hytter", hytter);
        model.addAttribute("leirId", leirId);
        return "hytte/hytte-liste";
    }

    // ========================
    // OPPRETTE NY HYTTE
    // ========================
    @PostMapping("/opprett")
    public String opprettHytte(@RequestParam String navn,
                               @RequestParam Long leirId,
                               Model model) {
        Optional<Leir> leirOpt = leirRepo.findById(leirId);

        if (leirOpt.isEmpty()) {
            model.addAttribute("feil", "Fant ikke leiren med ID: " + leirId);
            return "feilside";
        }

        Hytte nyHytte = new Hytte();
        nyHytte.setNavn(navn);
        nyHytte.setLeir(leirOpt.get());

        try {
            hytteRepo.save(nyHytte);
        } catch (Exception e) {
            model.addAttribute("feil", "Kunne ikke lagre hytten: " + e.getMessage());
            return "feilside";
        }

        return "redirect:/hytte/liste/" + leirId;
    }

    // ========================
    // REDIGERE HYTTE
    // ========================
    @GetMapping("/rediger/{id}")
    public String redigerHytteForm(@PathVariable Long id, Model model) {
        Optional<Hytte> hytteOpt = hytteRepo.findById(id);

        if (hytteOpt.isEmpty()) {
            model.addAttribute("feil", "Fant ikke hytten med ID: " + id);
            return "feilside";
        }

        model.addAttribute("hytte", hytteOpt.get());
        return "hytte/hytte-rediger";
    }

    @PostMapping("/rediger")
    public String lagreEndringer(@ModelAttribute Hytte hytte, Model model) {
        if (hytte.getLeir() == null || hytte.getLeir().getId() == null) {
            model.addAttribute("feil", "Hytten må være koblet til en leir.");
            return "feilside";
        }

        hytteRepo.save(hytte);
        return "redirect:/hytte/liste/" + hytte.getLeir().getId();
    }

    // ========================
    // SLETTE HYTTE
    // ========================
    @PostMapping("/slett/{id}")
    public String slettHytte(@PathVariable Long id, Model model) {
        Optional<Hytte> hytteOpt = hytteRepo.findById(id);

        if (hytteOpt.isEmpty()) {
            model.addAttribute("feil", "Fant ikke hytten med ID: " + id);
            return "feilside";
        }

        Long leirId = hytteOpt.get().getLeir().getId();

        try {
            hytteRepo.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("feil", "Kunne ikke slette hytten: " + e.getMessage());
            return "feilside";
        }

        return "redirect:/hytte/liste/" + leirId;
    }
}
