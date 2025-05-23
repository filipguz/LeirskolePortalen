package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.controller.service.DeltagerService;
import com.example.LeirskolePortalen.model.Deltaker;
import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/deltaker")
public class DeltakerController {

    private final DeltakerRepository deltakerRepo;
    private final LeirRepository leirRepo;
    private final DeltagerService deltagerService;

    public DeltakerController(DeltakerRepository deltakerRepo, LeirRepository leirRepo, DeltagerService deltagerService) {
        this.deltakerRepo = deltakerRepo;
        this.leirRepo = leirRepo;
        this.deltagerService = deltagerService;
    }

    @GetMapping("/ny/{leirId}")
    public String nyDeltaker(@PathVariable Long leirId, Model model) {
        Leir leir = leirRepo.findById(leirId).orElse(null);
        if (leir == null) {
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

    @PostMapping("/deltaker/lastopp")
    public String lastOppExcel(@RequestParam("file") MultipartFile file) {
        deltagerService.lesFraExcel(file); // ✅ Riktig: bruker instansmetode
        return "redirect:/deltaker/liste";
    }

    @GetMapping("/liste/{leirId}")
    public String liste(@PathVariable Long leirId, Model model) {
        model.addAttribute("deltakere", deltakerRepo.findByLeirId(leirId));
        model.addAttribute("leirId", leirId);
        return "deltaker/liste";
    }
}
