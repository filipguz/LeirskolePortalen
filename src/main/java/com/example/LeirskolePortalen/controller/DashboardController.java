// Definerer hvilken "pakke" denne filen tilhører. Pakker brukes for å organisere koden.
package com.example.LeirskolePortalen.controller;

// Importerer nødvendige klasser som brukes i denne filen
import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Comparator;

// Denne klassen er en "Controller" – det vil si at den bestemmer hva som skal skje
// når noen besøker en bestemt nettside (f.eks. /dashboard)
@Controller
@RequestMapping("/dashboard")  // Alle metoder i denne klassen gjelder for "/dashboard"-siden
public class DashboardController {

    // Disse to linjene lagrer referanser til databasene for leirer og deltakere
    private final LeirRepository leirRepo;
    private final DeltakerRepository deltakerRepo;

    // Dette er en "konstruktør" – den kjører når objektet opprettes
    // Her får vi tilgang til leir- og deltaker-databasene gjennom såkalte "repositories"
    public DashboardController(LeirRepository leirRepo, DeltakerRepository deltakerRepo) {
        this.leirRepo = leirRepo;
        this.deltakerRepo = deltakerRepo;
    }

    // Denne metoden bestemmer hva som skal vises på dashboardet (oversiktssiden)
    @GetMapping  // Gjelder når noen går til "/dashboard" i nettleseren
    public String visDashboard(Model model) {
        // Henter alle leirer fra databasen, men filtrerer ut de som er i fremtiden
        var fremtidigeLeirer = leirRepo.findAll()
                .stream()  // Gjør om listen til en strøm for å kunne filtrere og sortere
                .filter(leir -> leir.getStartDato() != null && leir.getStartDato().isAfter(LocalDate.now()))
                // Tar med kun leirer som starter etter i dag
                .sorted(Comparator.comparing(Leir::getStartDato))  // Sorterer leirene etter startdato
                .toList();  // Lager en ny liste av resultatet

        // Legger informasjonen til i "model"-objektet slik at den kan brukes i HTML-siden
        model.addAttribute("leirer", fremtidigeLeirer); // Listen over kommende leirer
        model.addAttribute("leirCount", leirRepo.count()); // Antall leirer totalt
        model.addAttribute("deltakerCount", deltakerRepo.count()); // Antall deltakere totalt
        model.addAttribute("ufordelteCount", deltakerRepo.countByHytteIsNull()); // Deltakere som ikke har fått plass i hytte

        // Returnerer navnet på HTML-siden som skal vises, i dette tilfellet "dashboard/oversikt"
        return "dashboard/oversikt";
    }
}
