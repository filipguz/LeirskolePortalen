package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leir")
public class LeirController {

    private final LeirRepository leirRepo;

    public LeirController(LeirRepository leirRepo) {
        this.leirRepo = leirRepo;
    }

    @GetMapping("/ny")
    public String nyLeirForm(Model model) {
        model.addAttribute("leir", new Leir());
        return "leir/ny";
    }

    @PostMapping("/lagre")
    public String lagreLeir(@ModelAttribute Leir leir) {
        leirRepo.save(leir);
        return "redirect:/leir/liste";
    }

    @GetMapping("/liste")
    public String visAlle(Model model) {
        model.addAttribute("leirer", leirRepo.findAll());
        return "leir/liste";
    }
}
