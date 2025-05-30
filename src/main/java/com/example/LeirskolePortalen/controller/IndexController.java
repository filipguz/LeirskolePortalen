package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final LeirRepository leirRepo;
    private final DeltakerRepository deltakerRepo;

    public IndexController(LeirRepository leirRepo, DeltakerRepository deltakerRepo) {
        this.leirRepo = leirRepo;
        this.deltakerRepo = deltakerRepo;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("leirCount", leirRepo.count());
        model.addAttribute("deltakerCount", deltakerRepo.count());
      // Legg til at jeg kan få filter på ulikeleirer...
       // model.addAttribute("alleLeirer", leirService.finnAlleKommende());
        return "index";
    }
}
