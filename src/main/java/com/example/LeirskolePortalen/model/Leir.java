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

    @Column(length = 1000)
    private String merknader;

    @ManyToOne
    private Bruker bruker;

    @OneToMany(mappedBy = "leir", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hytte> hytter = new ArrayList<>();

    @OneToMany(mappedBy = "leir", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deltaker> deltakere = new ArrayList<>();

    @ManyToMany
    private List<Skole> skoler = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "skole_id")
    private Skole skole;

    // ---------- Getters og Setters ----------

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

    public String getMerknader() { return merknader; }
    public void setMerknader(String merknader) { this.merknader = merknader; }

    public Bruker getBruker() { return bruker; }
    public void setBruker(Bruker bruker) { this.bruker = bruker; }

    public List<Hytte> getHytter() { return hytter; }
    public void setHytter(List<Hytte> hytter) { this.hytter = hytter; }

    public List<Deltaker> getDeltakere() { return deltakere; }
    public void setDeltakere(List<Deltaker> deltakere) { this.deltakere = deltakere; }

    public List<Skole> getSkoler() { return skoler; }
    public void setSkoler(List<Skole> skoler) { this.skoler = skoler; }

    public Skole getSkole() { return skole; }
    public void setSkole(Skole skole) { this.skole = skole; }
}

