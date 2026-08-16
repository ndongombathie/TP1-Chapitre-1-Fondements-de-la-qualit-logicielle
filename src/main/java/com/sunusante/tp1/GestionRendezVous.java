package com.sunusante.tp1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TP1 - Version fournie aux étudiants pour SunuSanté, l'appli de gestion de
 * rendez-vous de la clinique.
 *
 * Ce code FONCTIONNE : la qualité externe (ce que voit l'utilisateur) est
 * correcte. Mais la qualité interne laisse à désirer : une seule classe fait
 * la validation, le calcul du tarif, le stockage ET l'affichage (violation du
 * principe de responsabilité unique), et la logique de tarification est
 * recopiée à deux endroits (violation DRY).
 *
 * NE MODIFIEZ PAS CE FICHIER avant d'avoir lu le README de ce dossier :
 * vous avez d'abord besoin d'un filet de tests (voir GestionRendezVousTest).
 */
public class GestionRendezVous {

    // Chaque rendez-vous est stocké comme : [patient, type, date, vip, prix]
    private final List<String[]> rendezVous = new ArrayList<>();
    private Tarif tarif = null;
    
    //calcule le tarif de la consultation en fonction du type de consultation
    private void patientNull(String patient){
        if (patient == null || patient.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du patient est obligatoire");
        }
    }

    private void dateNull(String date){
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("La date est obligatoire");
        }
    }

    public double ajouterRendezVous(String patient, String type, String date, boolean estVip) {
        patientNull(patient);
        dateNull(date);
        LocalDate d = LocalDate.parse(date);
        tarif = new Tarif(type, d, 0, estVip);

        double prix;
        
        prix = tarif.calculeTarifTypeConsultation();

        prix = tarif.calculeTarifReductionWeekend();

        prix = tarif.calculeTarifReductionEstvip();

        prix = reductionTarif(patient, date, prix);
        
        rendezVous.add(new String[]{patient, type, date, String.valueOf(estVip), String.valueOf(prix)});

        System.out.println("Rendez-vous ajouté pour " + patient + " (" + type + ") le " + date + " - " + prix + " FCFA");

        return prix;
    }

    public void annulerRendezVous(String patient, String date) {
        patientNull(patient);
        dateNull(date);
        
        rendezVous.removeIf(r -> r[0].equals(patient) && r[2].equals(date));
        System.out.println("Rendez-vous annulé pour " + patient + " le " + date);
    }

    public void afficherRendezVous() {
        for (String[] r : rendezVous) {
            System.out.println(r[0] + " | " + r[1] + " | " + r[2] + " | VIP=" + r[3] + " | " + r[4] + " FCFA");
        }
    }

    public int nombreRendezVous(String patient, String date) {
        int count = 0;
        for (String[] r : rendezVous) {
            if (r[0].equals(patient) && r[2].equals(date)) {
                count++;
            }
        }
        return count;
    }

    // TODO (TP1, étape TDD) : ajoutez ici le tarif dégressif de 15% pour le
    // 2e rendez-vous (et les suivants) d'un même patient à la même date.
    // Écrivez d'abord le test dans GestionRendezVousTest (RED), faites-le
    // passer avec le code le plus simple possible (GREEN), puis nettoyez
    // (REFACTOR) en gardant tous les tests verts.
    public double reductionTarif(String patient, String date,double prix) {
        int count = nombreRendezVous(patient, date);
        if (count >= 2) {
            prix = prix - prix * 0.15;
        }
        return prix;
    }

    public List<String[]> getRendezVous() {
        return rendezVous;
    }
}
