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
    
    //calcule le tarif de la consultation en fonction du type de consultation
    private double calculeTarifTypeConsultation(String type){
        double prix;
        if (type.equals("GENERALISTE")) {
            prix = 5000;
        } else if (type.equals("SPECIALISTE")) {
            prix = 10000;
        } else if (type.equals("URGENCE")) {
            prix = 15000;
        } else {
            throw new IllegalArgumentException("Type de consultation inconnu: " + type);
        }
        return prix;
    }

    private double calculeTarifReductionWeekend(String type,LocalDate date,double prix){
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            if (type.equals("GENERALISTE")) {
                prix = prix + prix * 0.2;
            } else if (type.equals("SPECIALISTE")) {
                prix = prix + prix * 0.2;
            } else if (type.equals("URGENCE")) {
                prix = prix + prix * 0.2;
            }
        }
        return prix;
    }

     private double calculeTarifReductionEstvip(boolean estVip,String type,double prix){
        if (estVip) {
            if (type.equals("GENERALISTE")) {
                prix = prix - prix * 0.1;
            } else if (type.equals("SPECIALISTE")) {
                prix = prix - prix * 0.1;
            } else if (type.equals("URGENCE")) {
                prix = prix - prix * 0.1;
            }
        }
        return prix;
    }



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

        double prix;
        prix = calculeTarifTypeConsultation(type);

        LocalDate d = LocalDate.parse(date);
        prix = calculeTarifReductionWeekend(type,d,prix);

        prix = calculeTarifReductionEstvip(estVip,type,prix);

        prix = reductionTarif(patient, date,prix);
        
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

    public double calculerTotalFacture(String patient) {
        double total = 0;
        for (String[] r : rendezVous) {
            if (r[0].equals(patient)) {
                String type = r[1];
                boolean vip = Boolean.parseBoolean(r[3]);
                String date = r[2];

                double prix;
                prix = calculeTarifTypeConsultation(type);

                LocalDate d = LocalDate.parse(date);
                prix = calculeTarifReductionWeekend(type,d,prix);

                prix = calculeTarifReductionEstvip(vip,type,prix);

                total = total + prix;
            }
        }
        return total;
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
}
