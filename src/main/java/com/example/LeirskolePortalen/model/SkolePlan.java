package com.example.LeirskolePortalen.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
    public class SkolePlan {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String skolenavn;
        private String trinn;
        private int antallElever;

        @ManyToOne
        private UkePlan ukePlan;

        @OneToMany(mappedBy = "skolePlan", cascade = CascadeType.ALL)
        private List<Deltaker> deltakere;

        // Getters/setters
    }


