package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Hytte;
import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.HytteRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hytte")
public class HytteController {

    private final HytteRepository hytteRepo;
    private final LeirRepository leirRepo;

    public HytteController(HytteRepository hytteRepo, LeirRepository leirRepo) {
        this.hytteRepo = hytteRepo;
        this.leirRepo = leirRepo;
    }

    @GetMapping("/liste/{leirId}")
    public String visHytter(@PathVariable Long leirId, Model model) {
        List<Hytte> hytter = hytteRepo.findByLeirId(leirId);
        model.addAttribute("hytter", hytter);
        model.addAttribute("leirId", leirId);
        return "hytte/hytte-liste";
    }

    @PostMapping("/opprett")
    public String opprettHytte(@RequestParam String navn,
                               @RequestParam Long leirId) {
        Leir leir = leirRepo.findById(leirId).orElseThrow();
        Hytte hytte = new Hytte();
        hytte.setNavn(navn);
        hytte.setLeir(leir);
        hytteRepo.save(hytte);
        return "redirect:/hytte/liste/" + leirId;
    }

    @GetMapping("/rediger/{id}")
    public String redigerHytteForm(@PathVariable Long id, Model model) {
        Hytte hytte = hytteRepo.findById(id).orElseThrow();
        model.addAttribute("hytte", hytte);
        return "hytte/hytte-rediger";
    }

    @PostMapping("/rediger")
    public String lagreEndringer(@ModelAttribute Hytte hytte) {
        hytteRepo.save(hytte);
        return "redirect:/hytte/liste/" + hytte.getLeir().getId();
    }

    @PostMapping("/slett/{id}")
    public String slettHytte(@PathVariable Long id) {
        Long leirId = hytteRepo.findById(id).get().getLeir().getId();
        hytteRepo.deleteById(id);
        return "redirect:/hytte/liste/" + leirId;
    }
}
