package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class UkePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ukenummer;
    private LocalDate startDato;
    private LocalDate sluttDato;

    @OneToMany(mappedBy = "ukePlan", cascade = CascadeType.ALL)
    private List<SkolePlan> skoler;

    // Getters/setters
}