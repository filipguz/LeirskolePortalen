package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.model.Skole;
import com.example.LeirskolePortalen.model.SkolePlan;
import com.example.LeirskolePortalen.repository.LeirRepository;
import com.example.LeirskolePortalen.repository.SkolePlanRepository;
import com.example.LeirskolePortalen.repository.SkoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/skoleplan")
public class SkolePlanController {

    private final SkolePlanRepository skolePlanRepo;
    private final SkoleRepository skoleRepo;
    private final LeirRepository leirRepo;

    public SkolePlanController(SkolePlanRepository skolePlanRepo, SkoleRepository skoleRepo, LeirRepository leirRepo) {
        this.skolePlanRepo = skolePlanRepo;
        this.skoleRepo = skoleRepo;
        this.leirRepo = leirRepo;
    }

    @GetMapping("/ny")
    public String visForm(Model model) {
        model.addAttribute("skoler", skoleRepo.findAll());
        model.addAttribute("leirer", leirRepo.findAll());
        model.addAttribute("skoleplan", new SkolePlan());
        return "skoleplan/ny";
    }

    @PostMapping("/lagre")
    public String lagre(@ModelAttribute SkolePlan skoleplan) {
        skolePlanRepo.save(skoleplan);
        return "redirect:/dashboard";
    }
}
