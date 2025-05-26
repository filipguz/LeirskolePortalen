package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Skole;
import com.example.LeirskolePortalen.repository.SkoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skole")
public class SkoleController {

    private final SkoleRepository skoleRepo;

    public SkoleController(SkoleRepository skoleRepo) {
        this.skoleRepo = skoleRepo;
    }

    @GetMapping("/ny")
    public String nySkole(Model model) {
        model.addAttribute("skole", new Skole());
        return "skole/skjema";
    }

    @PostMapping("/lagre")
    public String lagre(@ModelAttribute Skole skole) {
        skoleRepo.save(skole);
        return "redirect:/skole/kunder";
    }

    @GetMapping("/kunder")
    public String alleSkoler(Model model) {
        model.addAttribute("skoler", skoleRepo.findAll());
        return "skole/liste";
    }
}
