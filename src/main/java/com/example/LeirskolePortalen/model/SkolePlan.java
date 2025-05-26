package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class SkolePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Skole skole;

    @ManyToOne
    private Leir leir;

    @ManyToOne
    private UkePlan ukePlan;

    @OneToMany(mappedBy = "skolePlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deltaker> deltakere;

    // --- Getters og Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Skole getSkole() {
        return skole;
    }

    public void setSkole(Skole skole) {
        this.skole = skole;
    }

    public Leir getLeir() {
        return leir;
    }

    public void setLeir(Leir leir) {
        this.leir = leir;
    }

    public UkePlan getUkePlan() {
        return ukePlan;
    }

    public void setUkePlan(UkePlan ukePlan) {
        this.ukePlan = ukePlan;
    }

    public List<Deltaker> getDeltakere() {
        return deltakere;
    }

    public void setDeltakere(List<Deltaker> deltakere) {
        this.deltakere = deltakere;
    }
}
