package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;

@Entity
public class Hytte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String navn;

    @ManyToOne
    private Leir leir;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }
    public Leir getLeir() { return leir; }
    public void setLeir(Leir leir) { this.leir = leir; }
}

