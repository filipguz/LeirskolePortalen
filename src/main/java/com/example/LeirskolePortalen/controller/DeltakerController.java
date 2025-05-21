package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Deltaker;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deltaker")
public class DeltakerController {

    private final DeltakerRepository deltakerRepo;

    public DeltakerController(DeltakerRepository deltakerRepo) {
        this.deltakerRepo = deltakerRepo;
    }

    @GetMapping("/oversikt")
    public String visOversikt(Model model) {
        List<Deltaker> deltakere = deltakerRepo.findAll();
        model.addAttribute("deltakere", deltakere);
        return "deltaker/oversikt";  // lag denne siden under /templates/deltaker/oversikt.html
    }

    @GetMapping("/ny")
    public String visNyttSkjema(Model model) {
        model.addAttribute("deltaker", new Deltaker());
        return "deltaker/ny";  // lag skjema for å legge til deltaker
    }

    @PostMapping
    public String lagre(@ModelAttribute Deltaker deltaker) {
        deltakerRepo.save(deltaker);
        return "redirect:/deltaker/oversikt";
    }
}
