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
    private List<SkolePlan> skolePlaner;

    // --- Getters og setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUkenummer() {
        return ukenummer;
    }

    public void setUkenummer(int ukenummer) {
        this.ukenummer = ukenummer;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public LocalDate getSluttDato() {
        return sluttDato;
    }

    public void setSluttDato(LocalDate sluttDato) {
        this.sluttDato = sluttDato;
    }

    public List<SkolePlan> getSkolePlaner() {
        return skolePlaner;
    }

    public void setSkolePlaner(List<SkolePlan> skolePlaner) {
        this.skolePlaner = skolePlaner;
    }
}
