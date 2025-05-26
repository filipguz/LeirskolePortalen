package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;

@Entity
public class Skole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn;
    private String kontaktperson;
    private String telefon;
    private String epost;

    // Gettere og settere
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public String getKontaktperson() { return kontaktperson; }
    public void setKontaktperson(String kontaktperson) { this.kontaktperson = kontaktperson; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getEpost() { return epost; }
    public void setEpost(String epost) { this.epost = epost; }
}
