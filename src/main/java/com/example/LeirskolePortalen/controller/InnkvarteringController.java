package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.HytteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/innkvartering")
public class InnkvarteringController {

    private final HytteRepository hytteRepo;
    private final DeltakerRepository deltakerRepo;

    public InnkvarteringController(HytteRepository hytteRepo, DeltakerRepository deltakerRepo) {
        this.hytteRepo = hytteRepo;
        this.deltakerRepo = deltakerRepo;
    }

    @GetMapping
    public String visFordeling(Model model) {
        model.addAttribute("hytter", hytteRepo.findAll());
        model.addAttribute("deltakere", deltakerRepo.findAll());
        return "innkvartering/fordeling"; // Thymeleaf-visning med drag & drop
    }

    @PostMapping("/flytt")
    @ResponseBody
    public ResponseEntity<String> flyttDeltaker(@RequestParam Long deltakerId, @RequestParam(required = false) Long hytteId) {
        var deltaker = deltakerRepo.findById(deltakerId).orElseThrow();

        if (hytteId != null) {
            var hytte = hytteRepo.findById(hytteId).orElseThrow();
            deltaker.setHytte(hytte);
        } else {
            deltaker.setHytte(null); // Fjern fra hytte
        }

        deltakerRepo.save(deltaker);
        return ResponseEntity.ok("Flyttet");
    }
}
