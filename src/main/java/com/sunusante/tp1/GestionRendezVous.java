package com.sunusante.tp1;
import java.time.LocalDate;
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
    private Tarif tarif = null;
    private Stockage stockage = new Stockage();
    //calcule le tarif de la consultation en fonction du type de consultation

    public double ajouterRendezVous(String patient, TypeConsultation consultation, String date, boolean estVip) {
        Validation v = new Validation(patient, date);
        v.patientNull();
        v.dateNull();

        LocalDate d = LocalDate.parse(date);
        tarif = new Tarif(consultation, d, patient , estVip);

        double prix;
        
        prix = tarif.calculeTarifTypeConsultation();

        prix = tarif.calculeTarifReductionWeekend();
        
        prix = tarif.calculeTarifReductionEstvip();
        
        prix = tarif.reductionTarif(date, stockage);

        stockage.save(patient,consultation,date,estVip,prix);

        System.out.println("Rendez-vous ajouté pour " + patient + " (" + consultation.getName() + ") le " + date + " - " + prix + " FCFA");

        return prix;
    }

    public void annulerRendezVous(String patient, String date) {
        Validation v = new Validation(patient, date);
        v.patientNull();
        v.dateNull();
        
        stockage.getRendezVous().removeIf(r -> r[0].equals(patient) && r[2].equals(date));
        System.out.println("Rendez-vous annulé pour " + patient + " le " + date);
    }

    public int nombreRendezVous(String patient, String date) {
        int count = 0;
        for (Object[] r : stockage.getRendezVous()) {
            if (r[0].equals(patient) && r[2].equals(date)) {
                count++;
            }
        }

        return count;
    }

    public List<Object[]> getRendezVous() {
        return this.stockage.getRendezVous();
    }
}
