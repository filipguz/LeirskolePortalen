package com.example.LeirskolePortalen.controller;


import com.example.LeirskolePortalen.model.Deltaker;
import com.example.LeirskolePortalen.model.Hytte;
import com.example.LeirskolePortalen.model.Innkvarering;
import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.HytteRepository;
import com.example.LeirskolePortalen.repository.InnkvareringRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/innkvarering")
public class InnkvareringController {
    private final LeirRepository leirRepo;
    private final HytteRepository hytteRepo;
    private final DeltakerRepository deltakerRepo;
    private final InnkvareringRepository innkvareringRepo;

    public InnkvareringController(
        LeirRepository leirRepo,
        HytteRepository hytteRepo,
        DeltakerRepository deltakerRepo,
        InnkvareringRepository innkvareringRepo) {
        this.leirRepo = leirRepo;
        this.hytteRepo = hytteRepo;
        this.deltakerRepo = deltakerRepo;
        this.innkvareringRepo = innkvareringRepo;
    }

    @GetMapping("/{leirId}")
    public String fordelingsside(@PathVariable Long leirId, Model model) {
        Leir leir = leirRepo.findById(leirId).orElseThrow();
        model.addAttribute("leir", leir);
        model.addAttribute("hytter", hytteRepo.findByLeirId(leirId));
        model.addAttribute("deltakere", deltakerRepo.findByLeirId(leirId));
        model.addAttribute("innkvareringer", innkvareringRepo.findByHytte_LeirId(leirId));
        return "innkvarering/fordeling";
    }

    @PostMapping("/lagre")
    public String lagreInnkvarering(
        @RequestParam Long hytteId,
        @RequestParam List<Long> deltakerIds
    ) {
        Hytte hytte = hytteRepo.findById(hytteId).orElseThrow();
        List<Deltaker> deltakere = deltakerRepo.findAllById(deltakerIds);

        Innkvarering innkvarering = new Innkvarering();
        innkvarering.setHytte(hytte);
        innkvarering.setDeltakere(deltakere);

        innkvareringRepo.save(innkvarering);
        return "redirect:/innkvarering/" + hytte.getLeir().getId();
    }
}
