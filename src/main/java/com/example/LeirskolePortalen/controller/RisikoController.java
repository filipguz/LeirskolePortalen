package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Risiko;
import com.example.LeirskolePortalen.service.RisikoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/risiko")
public class RisikoController {

    @Autowired
    private RisikoService risikoService;

    // ---------- READ: Vis skjema og liste over vurderinger ----------
    @GetMapping
    public String visSkjemaOgListe(Model model) {
        model.addAttribute("risikoListe", risikoService.hentAlleRisiko());
        model.addAttribute("risiko", new Risiko()); // For skjema
        return "risiko/ny";
    }

    // ---------- CREATE: Beregn og lagre ny risikovurdering ----------
    @PostMapping("/lagre")
    public String beregnOgLagreRisiko(@ModelAttribute Risiko risiko, Model model) {
        Risiko nyRisiko = risikoService.opprettRisiko(
                risiko.getAktivitet(),
                risiko.getRisikomoment(),
                risiko.getSkadeType(),
                risiko.getSannsynlighet(),
                risiko.getKonsekvens(),
                risiko.getAktivitetDetaljer()
        );

        model.addAttribute("risiko", nyRisiko);
        model.addAttribute("risikoListe", risikoService.hentAlleRisiko());
        return "risiko/resultat";
    }

    // ---------- UPDATE: Vis redigeringsskjema ----------
    @GetMapping("/rediger/{id}")
    public String redigerSkjema(@PathVariable Long id, Model model) {
        Risiko risiko = risikoService.hentRisikoVedId(id); // IKKE .orElseThrow
        model.addAttribute("risiko", risiko);
        return "risiko/rediger";
    }

    // ---------- UPDATE: Lagre oppdatert vurdering ----------
    @PostMapping("/oppdater")
    public String oppdaterRisiko(@ModelAttribute Risiko risiko) {
        risikoService.oppdaterRisiko(risiko);
        return "redirect:/risiko";
    }

    // ---------- DELETE: Slett vurdering ----------
    @PostMapping("/slett/{id}")
    public String slettRisiko(@PathVariable Long id) {
        risikoService.slettRisiko(id);
        return "redirect:/risiko";
    }
}
