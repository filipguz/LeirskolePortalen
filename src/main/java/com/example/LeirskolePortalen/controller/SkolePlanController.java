package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.model.Skole;
import com.example.LeirskolePortalen.model.SkolePlan;
import com.example.LeirskolePortalen.model.UkePlan;
import com.example.LeirskolePortalen.repository.LeirRepository;
import com.example.LeirskolePortalen.repository.SkolePlanRepository;
import com.example.LeirskolePortalen.repository.SkoleRepository;
import com.example.LeirskolePortalen.repository.UkePlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/skoleplan")
public class SkolePlanController {

    private final SkolePlanRepository skolePlanRepo;
    private final SkoleRepository skoleRepo;
    private final LeirRepository leirRepo;
    private final UkePlanRepository ukePlanRepo;

    public SkolePlanController(SkolePlanRepository skolePlanRepo,
                               SkoleRepository skoleRepo,
                               LeirRepository leirRepo,
                               UkePlanRepository ukePlanRepo) {
        this.skolePlanRepo = skolePlanRepo;
        this.skoleRepo = skoleRepo;
        this.leirRepo = leirRepo;
        this.ukePlanRepo = ukePlanRepo;
    }

    // --------- CREATE form ---------
    @GetMapping("/ny")
    public String nyPlan(Model model) {
        model.addAttribute("skoleplan", new SkolePlan());
        model.addAttribute("skoler", skoleRepo.findAll());
        model.addAttribute("leirer", leirRepo.findAll());
        model.addAttribute("ukeplaner", ukePlanRepo.findAll());
        return "skoleplan/skjema";
    }

    // --------- SAVE (create or update) ---------
    @PostMapping("/lagre")
    public String lagre(@RequestParam Long skole,
                        @RequestParam Long leir,
                        @RequestParam(required = false) Long ukePlan,
                        SkolePlan skoleplan) {

        Skole valgtSkole = skoleRepo.findById(skole)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skole ikke funnet"));
        Leir valgtLeir = leirRepo.findById(leir)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leir ikke funnet"));

        skoleplan.setSkole(valgtSkole);
        skoleplan.setLeir(valgtLeir);

        if (ukePlan != null) {
            UkePlan valgtUke = ukePlanRepo.findById(ukePlan).orElse(null);
            skoleplan.setUkePlan(valgtUke);
        } else {
            skoleplan.setUkePlan(null);
        }

        skolePlanRepo.save(skoleplan);
        return "redirect:/skoleplan/liste";
    }

    // --------- LIST all ---------
    @GetMapping("/liste")
    public String visAlle(Model model) {
        model.addAttribute("skoleplaner", skolePlanRepo.findAll());
        return "skoleplan/liste";
    }

    // --------- READ (single view) ---------
    @GetMapping("/vis/{id}")
    public String visEnkelt(@PathVariable Long id, Model model) {
        SkolePlan plan = skolePlanRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skoleplan ikke funnet"));
        model.addAttribute("skoleplan", plan);
        return "skoleplan/detalj";
    }

    // --------- EDIT form ---------
    @GetMapping("/rediger/{id}")
    public String rediger(@PathVariable Long id, Model model) {
        SkolePlan plan = skolePlanRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skoleplan ikke funnet"));

        model.addAttribute("skoleplan", plan);
        model.addAttribute("skoler", skoleRepo.findAll());
        model.addAttribute("leirer", leirRepo.findAll());
        model.addAttribute("ukeplaner", ukePlanRepo.findAll());
        return "skoleplan/skjema";
    }

    // --------- DELETE ---------
    @GetMapping("/slett/{id}")
    public String slett(@PathVariable Long id) {
        if (skolePlanRepo.existsById(id)) {
            skolePlanRepo.deleteById(id);
        }
        return "redirect:/skoleplan/liste";
    }
}
