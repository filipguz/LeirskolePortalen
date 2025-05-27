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
@RequestMapping("/hytte") // Alle ruter i denne klassen starter med /hytte
public class HytteController {

    private final HytteRepository hytteRepo;
    private final LeirRepository leirRepo;

    public HytteController(HytteRepository hytteRepo, LeirRepository leirRepo) {
        this.hytteRepo = hytteRepo;
        this.leirRepo = leirRepo;
    }

    // ---------- READ: Vise alle hytter for en gitt leir ----------

    // Henter og viser alle hytter som tilhører en bestemt leir (via leirId)
    @GetMapping("/liste/{leirId}")
    public String visHytter(@PathVariable Long leirId, Model model) {
        List<Hytte> hytter = hytteRepo.findByLeirId(leirId); // Hent hytter til leiren
        model.addAttribute("hytter", hytter);                // Legg til i modellen for visning i HTML
        model.addAttribute("leirId", leirId);
        return "hytte/hytte-liste";                          // Gå til HTML-visning av listen
    }

    // ---------- CREATE: Opprette ny hytte ----------

    // Lagrer ny hytte og knytter den til en bestemt leir
    @PostMapping("/opprett")
    public String opprettHytte(@RequestParam String navn,
                               @RequestParam Long leirId) {
        Leir leir = leirRepo.findById(leirId).orElseThrow(); // Finn leiren hytte skal tilhøre
        Hytte hytte = new Hytte();
        hytte.setNavn(navn);
        hytte.setLeir(leir); // Knytter hytten til leiren
        hytteRepo.save(hytte); // Lagre i databasen
        return "redirect:/hytte/liste/" + leirId; // Gå tilbake til listen over hytter
    }

    // ---------- UPDATE: Redigere eksisterende hytte ----------

    // Viser skjema for å redigere en hytte
    @GetMapping("/rediger/{id}")
    public String redigerHytteForm(@PathVariable Long id, Model model) {
        Hytte hytte = hytteRepo.findById(id).orElseThrow();  // Finn hytten basert på ID
        model.addAttribute("hytte", hytte);                  // Send hytten til HTML-skjema
        return "hytte/hytte-rediger";                        // Gå til redigeringsside
    }

    // Lagrer endringer i en hytte (fra skjema)
    @PostMapping("/rediger")
    public String lagreEndringer(@ModelAttribute Hytte hytte) {
        hytteRepo.save(hytte);                               // Oppdater eksisterende hytte
        return "redirect:/hytte/liste/" + hytte.getLeir().getId(); // Gå tilbake til leirens hytte-liste
    }

    // ---------- DELETE: Slette en hytte ----------

    // Sletter en hytte basert på ID
    @PostMapping("/slett/{id}")
    public String slettHytte(@PathVariable Long id) {
        Long leirId = hytteRepo.findById(id).get().getLeir().getId(); // Hent leirId for å kunne returnere til riktig liste
        hytteRepo.deleteById(id);                                     // Slett hytten
        return "redirect:/hytte/liste/" + leirId;                     // Tilbake til leirens hytte-liste
    }
}
