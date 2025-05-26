package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Comparator;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final LeirRepository leirRepo;
    private final DeltakerRepository deltakerRepo;

    public DashboardController(LeirRepository leirRepo, DeltakerRepository deltakerRepo) {
        this.leirRepo = leirRepo;
        this.deltakerRepo = deltakerRepo;
    }

    @GetMapping
    public String visDashboard(Model model) {
        var fremtidigeLeirer = leirRepo.findAll()
                .stream()
                .filter(leir -> leir.getStartDato() != null && leir.getStartDato().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Leir::getStartDato))
                .toList();

        model.addAttribute("leirer", fremtidigeLeirer);
        model.addAttribute("leirCount", leirRepo.count());
        model.addAttribute("deltakerCount", deltakerRepo.count());
        model.addAttribute("ufordelteCount", deltakerRepo.countByHytteIsNull());

        return "dashboard/oversikt";
    }
}
