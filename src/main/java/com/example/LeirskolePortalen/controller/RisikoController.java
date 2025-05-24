package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Risiko;
import com.example.LeirskolePortalen.service.RisikoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  // Marker klassen som en Spring MVC-kontroller
public class RisikoController {

    @Autowired // Injiserer RisikoService for å bruke logikken her
    private RisikoService risikoService;

    /**
     * GET-metode for å vise hovedskjemaet og listen over risikovurderinger.
     *
     * @param model - Spring-modellen som brukes for å sende data til HTML-siden.
     * @return - Navnet på HTML-siden ("index").
     */
    @GetMapping("/risiko")
    public String visSkjemaOgListe(Model model) {
        // Henter listen over alle risikovurderinger fra service-laget
        model.addAttribute("risikoListe", risikoService.hentAlleRisiko());
        return "risiko/ny"; // Viser index.html
    }

    /**
     * POST-metode for å beregne risiko basert på brukerens input.
     *
     * @param aktivitet - Aktiviteten som skal vurderes.
     * @param risikomoment - Spesifikke risikomomenter knyttet til aktiviteten.
     * @param skadeType - Type skade (personskade, miljøskade osv.).
     * @param sannsynlighet - Sannsynlighet for skade (0-5).
     * @param konsekvens - Konsekvensens alvorlighetsgrad (0-5).
     * @param model - Spring-modellen som brukes for å sende data til HTML-siden.
     * @return - Navnet på resultat-HTML-siden.
     */
    @PostMapping("/beregn")
    public String beregnRisiko(
            @RequestParam String aktivitet, // Henter "aktivitet" fra skjema
            @RequestParam String risikomoment, // Henter "risikomoment" fra skjema
            @RequestParam String skadeType, // Henter "skadeType" fra skjema
            @RequestParam int sannsynlighet, // Henter "sannsynlighet" fra skjema
            @RequestParam int konsekvens, // Henter "konsekvens" fra skjema
            @RequestParam(required = false) String aktivitetDetaljer,

            Model model) {

        // Bruker service-laget for å opprette og beregne en ny risiko
        Risiko nyRisiko = risikoService.opprettRisiko(aktivitet, risikomoment, skadeType, sannsynlighet, konsekvens, aktivitetDetaljer);

        // Legger beregnede data til modellen for visning i HTML
        model.addAttribute("aktivitet", nyRisiko.getAktivitet());
        model.addAttribute("risikomoment", nyRisiko.getRisikomoment());
        model.addAttribute("skadeType", nyRisiko.getSkadeType());
        model.addAttribute("sannsynlighet", nyRisiko.getSannsynlighet());
        model.addAttribute("konsekvens", nyRisiko.getKonsekvens());
        model.addAttribute("risiko", nyRisiko.getRisiko());
        model.addAttribute("vurdering", nyRisiko.getVurdering());
        model.addAttribute("aktivitetDetaljer", nyRisiko.getAktivitetDetaljer());

        // Oppdaterer listen med alle registrerte risikovurderinger
        model.addAttribute("risikoListe", risikoService.hentAlleRisiko());

        return "risiko/resultat"; // Går til resultat.html
    }
}