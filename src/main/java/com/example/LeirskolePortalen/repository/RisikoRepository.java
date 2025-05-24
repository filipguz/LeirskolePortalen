package com.example.LeirskolePortalen.repository;

import com.example.LeirskolePortalen.model.Risiko;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dette grensesnittet fungerer som et Repository for Risiko-entiteten.
 * Det gir tilgang til å utføre CRUD-operasjoner (Create, Read, Update, Delete)
 * og andre databaseoperasjoner på risiko-data.
 */
public interface RisikoRepository extends JpaRepository<Risiko, Long> {
    // Grensesnittet arver metoder fra JpaRepository:
    // - save(): For å lagre en ny risiko eller oppdatere en eksisterende
    // - findById(): For å finne en risiko basert på ID
    // - findAll(): For å hente alle risikoer
    // - deleteById(): For å slette en risiko basert på ID

    // JpaRepository tilbyr også støtte for paginering og sortering
    // dersom det er behov i fremtiden.

    // Tilpassede spørringer kan legges til her hvis nødvendig.
}
