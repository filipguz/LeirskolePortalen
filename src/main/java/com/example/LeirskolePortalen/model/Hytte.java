package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Hytte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Navn kan ikke være tomt")
    private String navn;

    @NotNull(message = "Hytten må være tilknyttet en leir")
    @ManyToOne
    @JoinColumn(name = "leir_id")
    private Leir leir;

    @ManyToOne
    @JoinColumn(name = "uke_plan_id")
    private UkePlan ukePlan;

    @OneToMany(mappedBy = "hytte", cascade = CascadeType.ALL)
    private List<Deltaker> deltakere;

    // === Gettere og settere ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
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

    @Override
    public String toString() {
        return "Hytte{" +
                "id=" + id +
                ", navn='" + navn + '\'' +
                '}';
    }
}
