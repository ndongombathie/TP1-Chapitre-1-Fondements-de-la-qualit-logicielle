package com.sunusante.tp1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Filet de tests de caractérisation : ils décrivent le comportement ACTUEL
 * du code (avant tout refactoring) pour que vous puissiez le modifier en
 * confiance. Ne les supprimez pas, ne changez pas leurs attentes : s'ils
 * cassent après un refactoring, c'est que le comportement a changé, pas
 * seulement le code.
 *
 * Rappel des dates utilisées : le 21/07/2026 est un mardi (jour de semaine),
 * le 25/07/2026 est un samedi (weekend).
 */
class GestionRendezVousTest {

    @Test
    void ajouterRendezVous_generaliste_semaine_tarifDeBase() {
        GestionRendezVous g = new GestionRendezVous();
        TypeConsultation consultation = new Generaliste();
        double prix = g.ajouterRendezVous("Awa Ndiaye", consultation, "2026-07-21", false);
        assertEquals(5000, prix);
    }

    @Test
    void ajouterRendezVous_specialiste_weekend_majore() {
        GestionRendezVous g = new GestionRendezVous();
        TypeConsultation consultation = new Specialite();
        double prix = g.ajouterRendezVous("Awa Ndiaye", consultation, "2026-07-25", false);
        assertEquals(12000, prix); // 10000 + 20% de majoration weekend
    }

    @Test
    void ajouterRendezVous_urgence_vip_reduit() {
        GestionRendezVous g = new GestionRendezVous();
        TypeConsultation consultation = new Urgence();
        double prix = g.ajouterRendezVous("Moussa Fall", consultation, "2026-07-21", true);
        assertEquals(13500, prix); // 15000 - 10% de réduction VIP
    }

    @Test
    void calculerTotalFacture_sommeLesRendezVousDuPatient() {
        GestionRendezVous g = new GestionRendezVous();
        g.ajouterRendezVous("Awa Ndiaye", new Generaliste(), "2026-07-21", false); // 5000
        g.ajouterRendezVous("Awa Ndiaye", new Specialite(), "2026-07-21", false); // 10000
        Facture f = new Facture("Awa Ndiaye", g.getRendezVous());
        assertEquals(15000, f.calculerTotalFacture());
    }

    @Test
    void calculerTotalFacture_estVip() {
        GestionRendezVous g = new GestionRendezVous();
        g.ajouterRendezVous("Awa Ndiaye", new Generaliste(), "2026-07-21", true); // 5000
        g.ajouterRendezVous("Awa Ndiaye", new Specialite(), "2026-07-21", true); // 10000
        Facture f = new Facture("Awa Ndiaye", g.getRendezVous());
        assertEquals(13500.f, f.calculerTotalFacture());
    }

    @Test
    void Test_annulerRendezVous() {
        GestionRendezVous g = new GestionRendezVous();
        g.ajouterRendezVous("Awa Ndiaye", new Generaliste(), "2026-07-21", true); // 5000
        g.ajouterRendezVous("Awa Ndiaye", new Specialite(), "2026-07-21", true); // 10000
        g.annulerRendezVous("Awa Ndiaye", "2026-07-21");
        assertEquals(0, g.nombreRendezVous("Awa Ndiaye","2026-07-21"));
    }

    @Test
    void Test_afficherRendezVous() {
        GestionRendezVous g = new GestionRendezVous();
        g.ajouterRendezVous("Awa Ndiaye", new Generaliste(), "2026-07-21", true); // 5000
        g.afficherRendezVous();
    }

    @Test
    void Test_patientNull() {
        GestionRendezVous g = new GestionRendezVous();
        assertThrows(IllegalArgumentException.class, () -> g.ajouterRendezVous(null, new Generaliste(), "2026-07-21", true));

    }

    @Test
    void Test_dateNull() {
        GestionRendezVous g = new GestionRendezVous();
        assertThrows(IllegalArgumentException.class, () -> g.ajouterRendezVous("Awa Ndiaye", new Generaliste(), null, true));

    }

    // TODO (TP1, étape TDD) : écrivez ici vos tests pour le tarif dégressif
    // du 2e rendez-vous du même patient le même jour, AVANT d'implémenter la
    // fonctionnalité dans GestionRendezVous (cycle RED -> GREEN -> REFACTOR).
    @Test
    void testnombreRendezVous(){
        GestionRendezVous g = new GestionRendezVous();
        g.ajouterRendezVous("Awa Ndiaye", new Generaliste(), "2026-07-21", false); // 5000
        g.ajouterRendezVous("Awa Ndiaye", new Specialite(), "2026-07-21", false); // 10000
        g.ajouterRendezVous("Awa Ndiaye", new Specialite(), "2026-07-21", false); // 10000
        assertEquals(3, g.nombreRendezVous("Awa Ndiaye","2026-07-21"));
    }


    @Test
    void testReductionTarif() {
        GestionRendezVous g = new GestionRendezVous();
        g.ajouterRendezVous("Awa Ndiaye", new Generaliste(), "2026-07-21", false); // 5000
        g.ajouterRendezVous("Awa Ndiaye", new Specialite(), "2026-07-21", false); // 10000
        double prix = g.ajouterRendezVous("Awa Ndiaye", new Specialite(), "2026-07-21", false); // 10000
        assertEquals(8500.f, prix);
    }
}
