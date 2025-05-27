/*package com.example.LeirskolePortalen.controller;


import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Controller
@RequestMapping("/leir")
public class LeirLogoController {

    private final LeirRepository leirRepo;

    public LeirLogoController(LeirRepository leirRepo) {
        this.leirRepo = leirRepo;
    }

    @PostMapping("/lastOppLogo")
    public String lastOppLogo(@RequestParam("logo") MultipartFile fil,
                              @RequestParam("leirId") Long leirId) throws IOException {

        Leir leir = leirRepo.findById(leirId).orElseThrow();

        if (!fil.isEmpty()) {
            // Lag unik filbane
            String filnavn = UUID.randomUUID() + "_" + fil.getOriginalFilename();
            Path mappe = Paths.get("src/main/resources/static/uploads");
            Files.createDirectories(mappe);

            // Lagre fil
            Path sti = mappe.resolve(filnavn);
            fil.transferTo(sti.toFile());

            // Oppdater Leir med URL
            leir.setLogoUrl("/uploads/" + filnavn);
            leirRepo.save(leir);
        }

        return "redirect:/leir/liste";
    }
}

 */
