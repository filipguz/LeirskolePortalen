package com.example.LeirskolePortalen.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/logo")
public class LogoController {

    private final Path uploadPath = Paths.get("src/main/resources/static/uploads");

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(uploadPath);
    }

    @PostMapping("/lastOpp")
    public String lastOpp(@RequestParam("fil") MultipartFile fil, RedirectAttributes redirectAttributes) throws IOException {
        if (fil.isEmpty()) return "redirect:/dashboard";

        String filnavn = UUID.randomUUID() + "-" + fil.getOriginalFilename();
        Path dest = uploadPath.resolve(filnavn);
        Files.copy(fil.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

        // Midlertidig enkel løsning: lagrer i session (evt. database senere)
        redirectAttributes.addFlashAttribute("logoUrl", "/uploads/" + filnavn);

        return "redirect:/dashboard";
    }
}
