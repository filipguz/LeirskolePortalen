package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Rom {
    @Id
    @GeneratedValue
    private Long id;

    private String navn;
    private int kapasitet;

    @ManyToOne
    private Leir leir;

    @ManyToMany
    private List<Deltaker> deltakere;
}
