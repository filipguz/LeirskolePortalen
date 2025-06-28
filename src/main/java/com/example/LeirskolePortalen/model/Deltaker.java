package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;

@Entity
public class Deltaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn;
    private Integer alder;
    private String allergi;
    private String kontaktperson;

    @ManyToOne
    private Hytte hytte;

    @ManyToOne // ✅ Dette manglet
    private Leir leir;

    @ManyToOne
    private SkolePlan skolePlan;

    // Getters og setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public Integer getAlder() { return alder; }
    public void setAlder(Integer alder) { this.alder = alder; }

    public String getAllergi() { return allergi; }
    public void setAllergi(String allergi) { this.allergi = allergi; }

    public String getKontaktperson() { return kontaktperson; }
    public void setKontaktperson(String kontaktperson) { this.kontaktperson = kontaktperson; }

    public Hytte getHytte() { return hytte; }
    public void setHytte(Hytte hytte) { this.hytte = hytte; }

    public Leir getLeir() { return leir; }
    public void setLeir(Leir leir) { this.leir = leir; }

    public SkolePlan getSkolePlan() {
        return skolePlan;
    }

    public void setSkolePlan(SkolePlan skolePlan) {
        this.skolePlan = skolePlan;
    }

}
