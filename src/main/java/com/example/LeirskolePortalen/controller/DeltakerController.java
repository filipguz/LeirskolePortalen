package com.example.LeirskolePortalen.controller;


import com.example.LeirskolePortalen.model.Deltaker;
import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/deltaker")
public class DeltakerController {

    private final DeltakerRepository deltakerRepo;
    private final LeirRepository leirRepo;

    public DeltakerController(DeltakerRepository deltakerRepo, LeirRepository leirRepo) {
        this.deltakerRepo = deltakerRepo;
        this.leirRepo = leirRepo;
    }

    @GetMapping("/ny/{leirId}")
    public String nyDeltaker(@PathVariable Long leirId, Model model) {
        Leir leir = leirRepo.findById(leirId).orElse(null);
        if (leir == null) {
            // Redirect til leir-liste hvis leir ikke finnes
            return "redirect:/leir/liste";
        }
        Deltaker deltaker = new Deltaker();
        deltaker.setLeir(leir);
        model.addAttribute("deltaker", deltaker);
        return "deltaker/ny";
    }

    @PostMapping("/lagre")
    public String lagre(@ModelAttribute Deltaker deltaker) {
        deltakerRepo.save(deltaker);
        return "redirect:/deltaker/liste/" + deltaker.getLeir().getId();
    }

    @GetMapping("/liste/{leirId}")
    public String liste(@PathVariable Long leirId, Model model) {
        model.addAttribute("deltakere", deltakerRepo.findByLeirId(leirId));
        model.addAttribute("leirId", leirId);
        return "deltaker/liste";
    }
}
