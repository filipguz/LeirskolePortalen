package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class Innkvarering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Hytte hytte;

    @ManyToMany
    private List<Deltaker> deltakere;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Hytte getHytte() { return hytte; }
    public void setHytte(Hytte hytte) { this.hytte = hytte; }
    public List<Deltaker> getDeltakere() { return deltakere; }
    public void setDeltakere(List<Deltaker> deltakere) { this.deltakere = deltakere; }
}
