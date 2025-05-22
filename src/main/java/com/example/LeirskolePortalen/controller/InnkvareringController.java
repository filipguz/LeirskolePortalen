/*package com.example.LeirskolePortalen.controller;
import com.example.LeirskolePortalen.model.Deltaker;
import com.example.LeirskolePortalen.model.Innkvarering;
import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.InnkvareringRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/innkvarering")
public class InnkvareringController {

    private final InnkvareringRepository innkvareringRepo;
    private final LeirRepository leirRepo;
    private final DeltakerRepository deltakerRepo;

    public InnkvareringController(InnkvareringRepository innkvareringRepo, LeirRepository leirRepo, DeltakerRepository deltakerRepo) {
        this.innkvareringRepo = innkvareringRepo;
        this.leirRepo = leirRepo;
        this.deltakerRepo = deltakerRepo;
    }

    // Vise skjema for ny innkvarering
    @GetMapping("/ny")
    public String visSkjema(@RequestParam Long leirId, Model model) {
        model.addAttribute("innkvarering", new Innkvarering());
        model.addAttribute("leirId", leirId);
        model.addAttribute("deltakere", deltakerRepo.findAll());
        return "innkvarering/ny";
    }

    // Lagre ny innkvarering
    @PostMapping
    public String lagre(@ModelAttribute Innkvarering innkvarering,
                        @RequestParam Long leirId,
                        @RequestParam List<Long> deltakerIds) {
        Leir leir = leirRepo.findById(leirId).orElseThrow();
        innkvarering.setLeir(leir);
        List<Deltaker> deltakere = deltakerRepo.findAllById(deltakerIds);
        innkvarering.setDeltakere(deltakere);
        innkvareringRepo.save(innkvarering);
        return "redirect:/innkvarering/oversikt?leirId=" + leirId;
    }

    // Vise oversikt over alle innkvareringer for én leir
    @GetMapping("/oversikt")
    public String visOversikt(@RequestParam Long leirId, Model model) {
        List<Innkvarering> innkvareringer = innkvareringRepo.findByLeirId(leirId);
        model.addAttribute("innkvareringer", innkvareringer);
        return "innkvarering/oversikt";
    }
}

 */