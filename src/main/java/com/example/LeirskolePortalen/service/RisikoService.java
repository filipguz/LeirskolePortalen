package com.example.LeirskolePortalen.service;

import com.example.LeirskolePortalen.model.Risiko;
import com.example.LeirskolePortalen.repository.RisikoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RisikoService {

    private final RisikoRepository risikoRepository;

    @Autowired
    public RisikoService(RisikoRepository risikoRepository) {
        this.risikoRepository = risikoRepository;
    }

    // Hent én risiko basert på ID
    public Risiko hentRisikoVedId(Long id) {
        return risikoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Risiko ikke funnet med id: " + id));
    }

    // Beregn risikoverdi
    public int beregnRisiko(int sannsynlighet, int konsekvens) {
        return sannsynlighet * konsekvens;
    }

    // Vurder risikonivå tekstlig
    public String vurderRisiko(int risiko) {
        if (risiko == 0) return "Ingen risiko (ubetydelig)";
        if (risiko <= 5) return "Lav risiko";
        if (risiko <= 15) return "Moderat risiko";
        if (risiko <= 25) return "Høy risiko";
        return "Svært høy risiko (kritisk)";
    }

    // Opprett og lagre ny risiko
    public Risiko opprettRisiko(String aktivitet, String risikomoment, String skadeType,
                                int sannsynlighet, int konsekvens, String aktivitetDetaljer) {

        int risikoScore = beregnRisiko(sannsynlighet, konsekvens);
        String vurdering = vurderRisiko(risikoScore);

        Risiko nyRisiko = new Risiko();
        nyRisiko.setAktivitet(aktivitet);
        nyRisiko.setRisikomoment(risikomoment);
        nyRisiko.setSkadeType(skadeType);
        nyRisiko.setSannsynlighet(sannsynlighet);
        nyRisiko.setKonsekvens(konsekvens);
        nyRisiko.setRisiko(risikoScore);
        nyRisiko.setAktivitetDetaljer(aktivitetDetaljer);
        nyRisiko.setVurdering(vurdering);

        return risikoRepository.save(nyRisiko);
    }

    // Hent alle risikoobjekter
    public List<Risiko> hentAlleRisiko() {
        return risikoRepository.findAll();
    }

    // Oppdater eksisterende risiko
    public void oppdaterRisiko(Risiko risiko) {
        if (!risikoRepository.existsById(risiko.getId())) {
            throw new RuntimeException("Kan ikke oppdatere. Risiko ikke funnet med id: " + risiko.getId());
        }

        int risikoScore = beregnRisiko(risiko.getSannsynlighet(), risiko.getKonsekvens());
        risiko.setRisiko(risikoScore);
        risiko.setVurdering(vurderRisiko(risikoScore));

        risikoRepository.save(risiko);
    }

    // Slett risiko etter ID
    public void slettRisiko(Long id) {
        if (!risikoRepository.existsById(id)) {
            throw new RuntimeException("Kan ikke slette. Risiko ikke funnet med id: " + id);
        }

        risikoRepository.deleteById(id);
    }
}
