package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;

@Entity
public class Bruker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String brukernavn;

    @Column(nullable = false)
    private String passord;  // Bør være bcrypt-hash når du lagrer!

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rolle rolle;

    // Konstruktører
    public Bruker() {}

    public Bruker(String brukernavn, String passord, Rolle rolle) {
        this.brukernavn = brukernavn;
        this.passord = passord;
        this.rolle = rolle;
    }

    // Getters og setters
    public Long getId() {
        return id;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }
}
