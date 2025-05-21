package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;



@Entity
public class Deltaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn;

    @ManyToOne
    @JoinColumn(name = "leir_id")
    private Leir leir;

    public Long getId() {
        return id;
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
}

