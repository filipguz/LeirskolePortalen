package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Leir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn;
    private LocalDate startDato;
    private LocalDate sluttDato;
    private String ansvarligLaerer;

    @OneToMany(mappedBy = "leir")
    private List<Hytte> hytter;

    @OneToMany(mappedBy = "leir")
    private List<Deltaker> deltakere;

    @ManyToOne
    private Bruker bruker;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public LocalDate getStartDato() { return startDato; }
    public void setStartDato(LocalDate startDato) { this.startDato = startDato; }

    public LocalDate getSluttDato() { return sluttDato; }
    public void setSluttDato(LocalDate sluttDato) { this.sluttDato = sluttDato; }

    public String getAnsvarligLaerer() { return ansvarligLaerer; }
    public void setAnsvarligLaerer(String ansvarligLaerer) { this.ansvarligLaerer = ansvarligLaerer; }

    public List<Hytte> getHytter() { return hytter; }
    public void setHytter(List<Hytte> hytter) { this.hytter = hytter; }

    public List<Deltaker> getDeltakere() { return deltakere; }
    public void setDeltakere(List<Deltaker> deltakere) { this.deltakere = deltakere; }
}
