package com.sunusante.tp1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Facture {
    private String patient ;
    private List<Object[]> rendezVous = new ArrayList<>();
    private Tarif tarif = null;

    public Facture() {
        this.patient = "";
        this.rendezVous = null;
    }
    
    public Facture(String patient, List<Object[]> rendezVous) {
        this.patient = patient;
        this.rendezVous = rendezVous;
    }


    public double calculerTotalFacture() {
        double total = 0;
        for (Object[] r : this.rendezVous) {
            if (r[0].equals(this.patient)) {
                TypeConsultation type = (TypeConsultation)r[1];
                boolean vip = Boolean.parseBoolean((String)r[3]);
                String date = (String)r[2];
                LocalDate d = LocalDate.parse(date);
                this.tarif= new Tarif(type, d, total, vip);

                double prix;
                prix = this.tarif.calculeTarifTypeConsultation();

                prix = this.tarif.calculeTarifReductionWeekend();

                prix = this.tarif.calculeTarifReductionEstvip();

                total = total + prix;
            }
        }
        return total;
    }

}
