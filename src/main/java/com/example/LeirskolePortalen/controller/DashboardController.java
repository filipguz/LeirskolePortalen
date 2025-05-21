package com.example.LeirskolePortalen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // Mapper både rot-URL og /dashboard til samme metode
    @GetMapping({"/", "/dashboard"})
    public String visDashboard() {
        // Returnerer template filen: src/main/resources/templates/dashboard/index.html
        return "dashboard/index";
    }
}
