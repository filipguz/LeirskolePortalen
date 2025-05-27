package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.model.Skole;
import com.example.LeirskolePortalen.model.SkolePlan;
import com.example.LeirskolePortalen.model.UkePlan;
import com.example.LeirskolePortalen.repository.LeirRepository;
import com.example.LeirskolePortalen.repository.SkolePlanRepository;
import com.example.LeirskolePortalen.repository.SkoleRepository;
import com.example.LeirskolePortalen.repository.UkePlanRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // ---------------- CREATE form ----------------
    @GetMapping("/ny")
    public String nyPlan(Model model) {
        model.addAttribute("skoleplan", new SkolePlan());
        model.addAttribute("skoler", skoleRepo.findAll());
        model.addAttribute("leirer", leirRepo.findAll());
        model.addAttribute("ukeplaner", ukePlanRepo.findAll());
        return "skoleplan/skjema";
    }

    // ---------------- SAVE (create or update) ----------------
    @PostMapping("/lagre")
    public String lagre(@RequestParam Long skole,
                        @RequestParam Long leir,
                        @RequestParam(required = false) Long ukePlan,
                        SkolePlan skoleplan) {

        skoleplan.setSkole(skoleRepo.findById(skole).orElseThrow());
        skoleplan.setLeir(leirRepo.findById(leir).orElseThrow());
        if (ukePlan != null) {
            skoleplan.setUkePlan(ukePlanRepo.findById(ukePlan).orElse(null));
        } else {
            skoleplan.setUkePlan(null);
        }

        skolePlanRepo.save(skoleplan);
        return "redirect:/skoleplan/liste";
    }

    // ---------------- LIST ----------------
    @GetMapping("/liste")
    public String visAlle(Model model) {
        model.addAttribute("skoleplaner", skolePlanRepo.findAll());
        return "skoleplan/liste";
    }

    // ---------------- EDIT ----------------
    @GetMapping("/rediger/{id}")
    public String rediger(@PathVariable Long id, Model model) {
        Optional<SkolePlan> plan = skolePlanRepo.findById(id);
        if (plan.isPresent()) {
            model.addAttribute("skoleplan", plan.get());
            model.addAttribute("skoler", skoleRepo.findAll());
            model.addAttribute("leirer", leirRepo.findAll());
            model.addAttribute("ukeplaner", ukePlanRepo.findAll());
            return "skoleplan/skjema";
        }
        return "redirect:/skoleplan/liste";
    }

    // ---------------- DELETE ----------------
    @GetMapping("/slett/{id}")
    public String slett(@PathVariable Long id) {
        if (skolePlanRepo.existsById(id)) {
            skolePlanRepo.deleteById(id);
        }
        return "redirect:/skoleplan/liste";
    }
}
