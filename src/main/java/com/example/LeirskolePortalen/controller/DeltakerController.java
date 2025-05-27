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
@RequestMapping("/deltaker")  // Gjelder alle ruter som starter med /deltaker
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

    // ---------- CREATE ----------

    // Viser skjema for å registrere ny deltaker til en bestemt leir
    @GetMapping("/ny/{leirId}")
    public String nyDeltaker(@PathVariable Long leirId, Model model) {
        Leir leir = leirRepo.findById(leirId).orElse(null);
        if (leir == null) {
            return "redirect:/leir/liste";  // Hvis leiren ikke finnes, gå tilbake til leirlisten
        }
        Deltaker deltaker = new Deltaker();
        deltaker.setLeir(leir);  // Knytter deltakeren til riktig leir
        model.addAttribute("deltaker", deltaker);
        return "deltaker/ny";  // Viser skjemaet for deltaker
    }

    // Lagrer en ny (eller oppdatert) deltaker
    @PostMapping("/lagre")
    public String lagre(@ModelAttribute Deltaker deltaker) {
        deltakerRepo.save(deltaker);
        return "redirect:/deltaker/liste/" + deltaker.getLeir().getId();
    }

    // ---------- READ ----------

    // Viser en liste over alle deltakere i en gitt leir
    @GetMapping("/liste/{leirId}")
    public String liste(@PathVariable Long leirId, Model model) {
        List<Deltaker> deltakere = deltakerRepo.findByLeirId(leirId);
        List<Hytte> hytter = hytteRepository.findByLeirId(leirId);
        model.addAttribute("deltakere", deltakere);
        model.addAttribute("hytter", hytter);
        model.addAttribute("leirId", leirId);
        return "deltaker/liste";
    }

    // ---------- UPDATE ----------

    // Viser redigeringsskjema for en deltaker basert på ID
    @GetMapping("/rediger/{id}")
    public String rediger(@PathVariable Long id, Model model) {
        Deltaker deltaker = deltakerRepo.findById(id).orElse(null);
        if (deltaker == null) {
            return "redirect:/leir/liste";
        }
        model.addAttribute("deltaker", deltaker);
        return "deltaker/ny";  // Bruker samme skjema som ved oppretting
    }

    // ---------- DELETE ----------

    // Sletter en deltaker basert på ID
    @GetMapping("/slett/{id}")
    public String slett(@PathVariable Long id) {
        Deltaker deltaker = deltakerRepo.findById(id).orElse(null);
        if (deltaker != null) {
            Long leirId = deltaker.getLeir().getId();  // Husk hvilken leir vi skal tilbake til
            deltakerRepo.deleteById(id);
            return "redirect:/deltaker/liste/" + leirId;
        }
        return "redirect:/leir/liste";  // Hvis ikke funnet
    }

    // ---------- EKSTRA: Masseimport fra Excel ----------

    // Leser Excel-fil og registrerer flere deltakere
    @PostMapping("/deltaker/lastopp")
    public String lastOppExcel(@RequestParam("file") MultipartFile file) {
        deltagerService.lesFraExcel(file);  // Delegerer til service som håndterer Excel
        return "redirect:/deltaker/liste";
    }

    // ---------- TILDELING AV HYTTE ----------

    // Kobler en deltaker til en bestemt hytte
    @PostMapping("/tildel-hytte")
    public String tildelHytte(@RequestParam Long deltakerId, @RequestParam Long hytteId) {
        Deltaker deltaker = deltakerRepo.findById(deltakerId).orElseThrow();
        Hytte hytte = hytteRepository.findById(hytteId).orElseThrow();
        deltaker.setHytte(hytte);
        deltakerRepo.save(deltaker);
        return "redirect:/deltaker/liste/" + deltaker.getLeir().getId();
    }
}
