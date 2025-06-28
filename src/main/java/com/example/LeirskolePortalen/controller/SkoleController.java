package com.example.LeirskolePortalen.controller;

import com.example.LeirskolePortalen.model.Skole;
import com.example.LeirskolePortalen.repository.SkoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/skole")
public class SkoleController {

    private final SkoleRepository skoleRepo;

    public SkoleController(SkoleRepository skoleRepo) {
        this.skoleRepo = skoleRepo;
    }

    // ----------- CREATE -----------

    @GetMapping("/ny")
    public String nySkole(Model model) {
        model.addAttribute("skole", new Skole());
        return "skole/skjema";
    }

    @PostMapping("/lagre")
    @ResponseBody
    public ResponseEntity<?> lagreSkoleViaAjax(@ModelAttribute Skole skole) {
        try {
            Skole lagret = skoleRepo.save(skole);
            return ResponseEntity.ok(lagret); // Returnerer JSON med skoleobjektet
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("feil", "Kunne ikke lagre skole."));
        }
    }


    // ----------- READ -----------

    @GetMapping("/kunder")
    public String alleSkoler(Model model) {
        model.addAttribute("skoler", skoleRepo.findAll());
        return "skole/liste";
    }

    // ----------- UPDATE -----------

    @GetMapping("/rediger/{id}")
    public String rediger(@PathVariable Long id, Model model) {
        Optional<Skole> skole = skoleRepo.findById(id);
        if (skole.isPresent()) {
            model.addAttribute("skole", skole.get());
            return "skole/skjema";
        } else {
            return "redirect:/skole/kunder";
        }
    }

    // ----------- DELETE -----------

    @PostMapping("/slett/{id}")
    public String slett(@PathVariable Long id, Model model) {
        try {
            skoleRepo.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("feil", "Kunne ikke slette skolen. Den er kanskje i bruk.");
            model.addAttribute("skoler", skoleRepo.findAll());
            return "skole/liste";
        }
        return "redirect:/skole/kunder";
    }
}
