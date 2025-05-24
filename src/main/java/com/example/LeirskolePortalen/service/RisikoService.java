package com.example.LeirskolePortalen.service;

import com.example.LeirskolePortalen.model.Risiko;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * RisikoService inneholder forretningslogikken for håndtering av risiko.
 * Dette inkluderer beregning, vurdering og lagring av risikoobjekter.
 */
@Service
public class RisikoService {

    // En liste for å midlertidig lagre risikoobjekter i minnet
    private List<Risiko> risikoListe = new ArrayList<>();

    /**
     * Beregner risikoverdien basert på sannsynlighet og konsekvens.
     * @param sannsynlighet Verdien for sannsynlighet (1-5).
     * @param konsekvens Verdien for konsekvens (1-5).
     * @return Produktet av sannsynlighet og konsekvens.
     */
    public int beregnRisiko(int sannsynlighet, int konsekvens) {
        return sannsynlighet * konsekvens;
    }

    /**
     * Vurderer graden av risiko basert på risikoverdien.
     * @param risiko Verdien som representerer risiko.
     * @return En tekstlig vurdering av risikograden.
     */
    public String vurderRisiko(int risiko) {
        if (risiko == 0) return "Ingen risiko (ubetydelig)";
        if (risiko <= 5) return "Lav risiko";
        if (risiko <= 15) return "Moderat risiko";
        if (risiko <= 25) return "Høy risiko";
        return "Svært høy risiko (kritisk)";
    }

    /**
     * Legger til et risikoobjekt i listen og logger handlingen.
     * @param risiko Risiko-objektet som skal lagres.
     */
    public void lagreIRisikoListe(Risiko risiko) {
        risikoListe.add(risiko);
    }

    /**
     * Henter alle risikoobjekter fra listen.
     * @return En liste med alle lagrede risikoer.
     */
    public List<Risiko> hentAlleRisiko() {
        return risikoListe;
    }

    /**
     * Oppretter et nytt risikoobjekt, beregner risikoen og vurderer graden,
     * og lagrer det i listen.
     * @param aktivitet Aktiviteten som risikoen gjelder for.
     * @param risikomoment Det spesifikke risikomomentet.
     * @param skadeType Typen skade risikoen kan medføre.
     * @param sannsynlighet Verdien for sannsynlighet (1-5).
     * @param konsekvens Verdien for konsekvens (1-5).
     * @param aktivitesdetaljer Litt mer detaljer om aktivitet
     * @return Det nyopprettede risikoobjektet.
     */
    public Risiko opprettRisiko(String aktivitet, String risikomoment, String skadeType, int sannsynlighet, int konsekvens, String aktivitesdetaljer) {
        // Beregn risikoskår og vurdering
        int risikoScore = beregnRisiko(sannsynlighet, konsekvens);
        String vurdering = vurderRisiko(risikoScore);

        // Opprett et nytt risikoobjekt med de beregnede verdiene
        Risiko nyRisiko = new Risiko();
        nyRisiko.setAktivitet(aktivitet);
        nyRisiko.setRisikomoment(risikomoment);
        nyRisiko.setSkadeType(skadeType);
        nyRisiko.setSannsynlighet(sannsynlighet);
        nyRisiko.setKonsekvens(konsekvens);
        nyRisiko.setRisiko(risikoScore);
        nyRisiko.setAktivitetDetaljer(aktivitesdetaljer);
        nyRisiko.setVurdering(vurderRisiko(risikoScore));

        // Lagre risikoen i listen
        lagreIRisikoListe(nyRisiko);
        return nyRisiko;
    }
}
