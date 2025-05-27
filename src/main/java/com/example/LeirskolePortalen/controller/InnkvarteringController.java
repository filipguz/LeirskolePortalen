package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.HytteRepository;
import com.example.LeirskolePortalen.model.Deltaker;
import com.example.LeirskolePortalen.model.Hytte;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/innkvartering") // Alle ruter i denne klassen starter med /innkvartering
public class InnkvarteringController {

    private final HytteRepository hytteRepo;
    private final DeltakerRepository deltakerRepo;

    // Konstruktor – gir tilgang til databasen for hytter og deltakere
    public InnkvarteringController(HytteRepository hytteRepo, DeltakerRepository deltakerRepo) {
        this.hytteRepo = hytteRepo;
        this.deltakerRepo = deltakerRepo;
    }

    // ---------- READ: Viser oversikt over deltakere og deres hyttefordeling ----------

    @GetMapping
    public String visFordeling(Model model) {
        model.addAttribute("hytter", hytteRepo.findAll());      // Alle hytter
        model.addAttribute("deltakere", deltakerRepo.findAll()); // Alle deltakere
        return "innkvartering/fordeling";                       // Viser HTML-side med drag & drop
    }

    // ---------- UPDATE: Flytt deltaker til en ny hytte eller fjern ----------

    // Kalles fra frontend (f.eks. via JavaScript) for å endre deltakers plassering
    @PostMapping("/flytt")
    @ResponseBody // Returnerer tekst som respons (ikke HTML-side)
    public ResponseEntity<String> flyttDeltaker(@RequestParam Long deltakerId,
                                                @RequestParam(required = false) Long hytteId) {
        // Hent deltaker fra databasen eller kast feil hvis ikke funnet
        Deltaker deltaker = deltakerRepo.findById(deltakerId).orElseThrow();

        if (hytteId != null) {
            // Hvis ny hytte er angitt, finn den og tildel
            Hytte hytte = hytteRepo.findById(hytteId).orElseThrow();
            deltaker.setHytte(hytte);
        } else {
            // Hvis ingen hytte er valgt, fjern deltaker fra hytte
            deltaker.setHytte(null);
        }

        deltakerRepo.save(deltaker); // Lagre endringen
        return ResponseEntity.ok("Flyttet");
    }

    // ---------- CREATE: (valgfritt) Sett deltaker direkte i hytte ved opprettelse ----------

    @PostMapping("/tildel")
    @ResponseBody
    public ResponseEntity<String> tildelNy(@RequestParam Long deltakerId, @RequestParam Long hytteId) {
        Deltaker deltaker = deltakerRepo.findById(deltakerId).orElseThrow();
        Hytte hytte = hytteRepo.findById(hytteId).orElseThrow();

        deltaker.setHytte(hytte);
        deltakerRepo.save(deltaker);
        return ResponseEntity.ok("Tildelt");
    }

    // ---------- DELETE: Fjern deltaker fra hytte direkte ----------

    @PostMapping("/fjern")
    @ResponseBody
    public ResponseEntity<String> fjernDeltakerFraHytte(@RequestParam Long deltakerId) {
        Deltaker deltaker = deltakerRepo.findById(deltakerId).orElseThrow();
        deltaker.setHytte(null); // Fjerner tilknytningen
        deltakerRepo.save(deltaker);
        return ResponseEntity.ok("Fjernet fra hytte");
    }
}
