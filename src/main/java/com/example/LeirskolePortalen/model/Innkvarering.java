package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Innkvarering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn; // Navn på hytte eller rom

    @ManyToOne
    private Leir leir;

    @ManyToMany
    private List<Deltaker> deltakere;

    // Gettere og settere
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public Leir getLeir() { return leir; }
    public void setLeir(Leir leir) { this.leir = leir; }

    public List<Deltaker> getDeltakere() { return deltakere; }
    public void setDeltakere(List<Deltaker> deltakere) { this.deltakere = deltakere; }
}