package com.sunusante.tp1;

import java.util.ArrayList;
import java.util.List;

public class Stockage {

    private final List<Object[]> rendezVous = new ArrayList<>();

    public void save(
            String patient,
            TypeConsultation consultation,
            String date,
            boolean estVip,
            double prix) {

        rendezVous.add(new Object[]{
            patient,
            consultation,
            date,
            estVip,
            prix
        });
    }

    public List<Object[]> getRendezVous() {
        return rendezVous;
    }

    public void afficherRendezVous() {
        for (Object[] r : rendezVous) {
            System.out.println(
                r[0] + " | " +
                r[1] + " | " +
                r[2] + " | VIP=" +
                r[3] + " | " +
                r[4] + " FCFA"
            );
        }
    }
}
