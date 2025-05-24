package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.service.DeltagerService;
import com.example.LeirskolePortalen.model.Deltaker;
import com.example.LeirskolePortalen.model.Hytte;
import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.HytteRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/deltaker")
public class DeltakerController {

    private final DeltakerRepository deltakerRepo;
    private final LeirRepository leirRepo;
    private final HytteRepository hytteRepository;
    private final DeltagerService deltagerService;

    public DeltakerController(DeltakerRepository deltakerRepo,
                              LeirRepository leirRepo,
                              DeltagerService deltagerService,
                              HytteRepository hytteRepository) {
        this.deltakerRepo = deltakerRepo;
        this.leirRepo = leirRepo;
        this.deltagerService = deltagerService;
        this.hytteRepository = hytteRepository;
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
        deltagerService.lesFraExcel(file);
        return "redirect:/deltaker/liste";
    }

    @GetMapping("/liste/{leirId}")
    public String liste(@PathVariable Long leirId, Model model) {
        List<Deltaker> deltakere = deltakerRepo.findByLeirId(leirId);
        List<Hytte> hytter = hytteRepository.findByLeirId(leirId);
        model.addAttribute("deltakere", deltakere);
        model.addAttribute("hytter", hytter);
        model.addAttribute("leirId", leirId);
        return "deltaker/liste";
    }

    @PostMapping("/tildel-hytte")
    public String tildelHytte(@RequestParam Long deltakerId, @RequestParam Long hytteId) {
        Deltaker deltaker = deltakerRepo.findById(deltakerId).orElseThrow();
        Hytte hytte = hytteRepository.findById(hytteId).orElseThrow();
        deltaker.setHytte(hytte);
        deltakerRepo.save(deltaker);
        return "redirect:/deltaker/liste/" + deltaker.getLeir().getId();
    }
}
