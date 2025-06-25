package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Entity
public class Leir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn;
    private LocalDate startDato;
    private LocalDate sluttDato;
    private String ansvarligLaerer;

    private Integer antallJenter;
    private Integer antallGutter;
    private Integer antallElever;

    private Integer uke;

    //private boolean sendtOpplysninger;
    //private boolean mottattBekreftelse;
    //private boolean mottattBestilling;

    @Column(length = 1000)
    private String merknader;

    @ManyToOne
    private Bruker bruker;

    @OneToMany(mappedBy = "leir")
    private List<Hytte> hytter;

    @OneToMany(mappedBy = "leir")
    private List<Deltaker> deltakere;

    @ManyToMany // eller @OneToMany, avhengig av relasjonen
    private List<Skole> skoler = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "skole_id") // Valgfritt: navnet på kolonnen i databasen
    private Skole skole;




    // ---------- Getters og Setters ----------

    public List<Skole> getSkoler() {
        return skoler;
    }

    // Setter for skoler
    public void setSkoler(List<Skole> skoler) {
        this.skoler = skoler;
    }

    public Skole getSkole() { return skole; }
    public void setSkole(Skole skole) { this.skole = skole; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public LocalDate getStartDato() { return startDato; }
    public void setStartDato(LocalDate startDato) { this.startDato = startDato; }

    public LocalDate getSluttDato() { return sluttDato; }
    public void setSluttDato(LocalDate sluttDato) { this.sluttDato = sluttDato; }

    public String getAnsvarligLaerer() { return ansvarligLaerer; }
    public void setAnsvarligLaerer(String ansvarligLaerer) { this.ansvarligLaerer = ansvarligLaerer; }

    public Integer getAntallJenter() { return antallJenter; }
    public void setAntallJenter(Integer antallJenter) { this.antallJenter = antallJenter; }

    public Integer getAntallGutter() { return antallGutter; }
    public void setAntallGutter(Integer antallGutter) { this.antallGutter = antallGutter; }

    public Integer getAntallElever() { return antallElever; }
    public void setAntallElever(Integer antallElever) { this.antallElever = antallElever; }

    public Integer getUke() { return uke; }
    public void setUke(Integer uke) { this.uke = uke; }

    //public boolean isSendtOpplysninger() { return sendtOpplysninger; }
   // public void setSendtOpplysninger(boolean sendtOpplysninger) { this.sendtOpplysninger = sendtOpplysninger; }

    //public boolean isMottattBekreftelse() { return mottattBekreftelse; }
    //public void setMottattBekreftelse(boolean mottattBekreftelse) { this.mottattBekreftelse = mottattBekreftelse; }

    //public boolean isMottattBestilling() { return mottattBestilling; }
    //public void setMottattBestilling(boolean mottattBestilling) { this.mottattBestilling = mottattBestilling; }

    public String getMerknader() { return merknader; }
    public void setMerknader(String merknader) { this.merknader = merknader; }

    public Bruker getBruker() { return bruker; }
    public void setBruker(Bruker bruker) { this.bruker = bruker; }

    public List<Hytte> getHytter() { return hytter; }
    public void setHytter(List<Hytte> hytter) { this.hytter = hytter; }

    public List<Deltaker> getDeltakere() { return deltakere; }
    public void setDeltakere(List<Deltaker> deltakere) { this.deltakere = deltakere; }

    //public List<SkolePlan> getSkoleplan() { return skoleplan; }
   // public void setSkoleplan(List<SkolePlan> skoleplan) { this.skoleplan = skoleplan; }
}








/*
@Entity
public class Leir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn;
    private LocalDate startDato;
    private LocalDate sluttDato;
    private String ansvarligLaerer;

    @OneToMany(mappedBy = "leir")
    private List<Hytte> hytter;

    @OneToMany(mappedBy = "leir")
    private List<Deltaker> deltakere;

    @OneToMany(mappedBy = "leir")
    private List<SkolePlan> skoleplan;

    /*@Column(name = "logo_url")
    private String logoUrl;

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }



    public void setSkoleplan(List<SkolePlan> skoleplan) {
        this.skoleplan = skoleplan;
    }
    public List<SkolePlan> getSkoleplan() {
        return skoleplan;
    }
    @ManyToOne
    private Bruker bruker;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public LocalDate getStartDato() { return startDato; }
    public void setStartDato(LocalDate startDato) { this.startDato = startDato; }

    public LocalDate getSluttDato() { return sluttDato; }
    public void setSluttDato(LocalDate sluttDato) { this.sluttDato = sluttDato; }

    public String getAnsvarligLaerer() { return ansvarligLaerer; }
    public void setAnsvarligLaerer(String ansvarligLaerer) { this.ansvarligLaerer = ansvarligLaerer; }

    public List<Hytte> getHytter() { return hytter; }
    public void setHytter(List<Hytte> hytter) { this.hytter = hytter; }

    public List<Deltaker> getDeltakere() { return deltakere; }
    public void setDeltakere(List<Deltaker> deltakere) { this.deltakere = deltakere; }
}

 */
