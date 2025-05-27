package com.example.LeirskolePortalen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Risiko {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aktivitet;
    private String risikomoment;
    private String skadeType;
    private int sannsynlighet;
    private int konsekvens;
    private int risiko;
    private String aktivitetDetaljer;
    private String vurdering;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAktivitet() {
        return aktivitet;
    }

    public void setAktivitet(String aktivitet) {
        this.aktivitet = aktivitet;
    }

    public String getSkadeType() {
        return skadeType;
    }

    public void setSkadeType(String skadeType) {
        this.skadeType = skadeType;
    }

    public int getSannsynlighet() {
        return sannsynlighet;
    }

    public void setSannsynlighet(int sannsynlighet) {
        this.sannsynlighet = sannsynlighet;
    }

    public int getKonsekvens() {
        return konsekvens;
    }

    public void setKonsekvens(int konsekvens) {
        this.konsekvens = konsekvens;
    }

    public int getRisiko() {
        return risiko;
    }

    public void setRisiko(int risiko) {
        this.risiko = risiko;
    }

    public String getAktivitetDetaljer() {
        return aktivitetDetaljer;
    }

    public void setAktivitetDetaljer(String aktivitetDetaljer) {
        this.aktivitetDetaljer = aktivitetDetaljer;
    }

    public String getVurdering() {
        return vurdering;
    }

    public void setVurdering(String vurdering) {
        this.vurdering = vurdering;
    }

    public String getRisikomoment() {
        return risikomoment;
    }

    public void setRisikomoment(String risikomoment) {
        this.risikomoment = risikomoment;
    }
}
