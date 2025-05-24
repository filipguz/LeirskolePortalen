package com.example.LeirskolePortalen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Dette er en entitetsklasse som representerer en risiko.
 * Klassen brukes til å lagre og hente risikodata fra databasen.
 */
@Entity // Marker klassen som en databaseentitet for JPA
public class Risiko {

    @Id // Markerer dette feltet som en unik identifikator for entiteten
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-genererer verdier for id
    private Long id; // Unik ID for hver risiko

    private String aktivitet; // Aktiviteten som blir vurdert
    private String risikomoment; // Det spesifikke risikomomentet i aktiviteten
    private String skadeType; // Type skade (f.eks. personskade, materiell skade)
    private int sannsynlighet; // Sannsynlighet for at skaden oppstår (0-5)
    private int konsekvens; // Alvorlighetsgrad av konsekvensen (0-5)
    private int risiko; // Risikoen beregnet som sannsynlighet * konsekvens
    private String aktivitesdetaljer;
    private String vurdering; // Tekstbasert vurdering av risikoen (lav, moderat, høy, kritisk)

    // Getters og setters for alle felter
    // Disse gir tilgang til å hente og sette verdier for hvert felt

    public Long getId() {
        return id; // Returnerer unik ID for risikoen
    }

    public void setId(Long id) {
        this.id = id; // Setter unik ID
    }

    public String getAktivitet() {
        return aktivitet; // Returnerer aktiviteten
    }

    public void setAktivitet(String aktivitet) {
        this.aktivitet = aktivitet; // Setter aktiviteten
    }

    public String getSkadeType() {
        return skadeType; // Returnerer type skade
    }

    public void setSkadeType(String skadeType) {
        this.skadeType = skadeType; // Setter type skade
    }

    public int getSannsynlighet() {
        return sannsynlighet; // Returnerer sannsynlighet
    }

    public void setSannsynlighet(int sannsynlighet) {
        this.sannsynlighet = sannsynlighet; // Setter sannsynlighet
    }

    public int getKonsekvens() {
        return konsekvens; // Returnerer konsekvens
    }

    public void setKonsekvens(int konsekvens) {
        this.konsekvens = konsekvens; // Setter konsekvens
    }

    public int getRisiko() {
        return risiko; // Returnerer beregnet risiko
    }

    public void setRisiko(int risiko) {
        this.risiko = risiko; // Setter beregnet risiko
    }
    public String getAktivitetDetaljer() {
        return aktivitesdetaljer;
    }

    public void setAktivitetDetaljer(String aktivitetDetaljer) {
        this.aktivitesdetaljer = aktivitetDetaljer;
    }


    public String getVurdering() {
        return vurdering; // Returnerer vurderingen av risikoen
    }

    public void setVurdering(String vurdering) {
        this.vurdering = vurdering; // Setter vurderingen av risikoen
    }

    public String getRisikomoment() {
        return risikomoment; // Returnerer risikomomentet
    }

    public void setRisikomoment(String risikomoment) {
        this.risikomoment = risikomoment; // Setter risikomomentet
    }
}

