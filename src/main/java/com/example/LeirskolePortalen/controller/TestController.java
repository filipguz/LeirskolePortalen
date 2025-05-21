package com.example.LeirskolePortalen.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String testHtml(Model model) {
        model.addAttribute("melding", "Hvis du ser denne teksten, fungerer HTML-koblingen!");
        return "test";
    }
}

