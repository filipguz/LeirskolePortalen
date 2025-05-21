package com.example.LeirskolePortalen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Leir {
    @Id
    @GeneratedValue
    private Long id;

    private String navn;
    private LocalDate startDato;
    private LocalDate sluttDato;
    private String skole;
    private String ansvarligLaerer;

    // Gettere og settere
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public LocalDate getStartDato() { return startDato; }
    public void setStartDato(LocalDate startDato) { this.startDato = startDato; }

    public LocalDate getSluttDato() { return sluttDato; }
    public void setSluttDato(LocalDate sluttDato) { this.sluttDato = sluttDato; }

    public String getSkole() { return skole; }
    public void setSkole(String skole) { this.skole = skole; }

    public String getAnsvarligLaerer() { return ansvarligLaerer; }
    public void setAnsvarligLaerer(String ansvarligLaerer) { this.ansvarligLaerer = ansvarligLaerer; }
}
