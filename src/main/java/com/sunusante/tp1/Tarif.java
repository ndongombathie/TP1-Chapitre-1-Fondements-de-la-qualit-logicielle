package com.sunusante.tp1;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Tarif {
    private TypeConsultation consultation ;
    private LocalDate date;
    private boolean estVip;
    private double prix;
    private String patient;

    public Tarif(){
        this.consultation = null;
        this.date = null;
        this.prix = 0.0;
    }

    public Tarif(TypeConsultation type,LocalDate date,String patient,boolean estVip){
        this.consultation=type;
        this.date=date;
        this.estVip=estVip;
        this.patient=patient;
    }

    public double calculeTarifTypeConsultation(){
        this.prix = this.consultation.calculeTarifConsultation();
        return this.prix;
    }


    public double calculeTarifReductionWeekend(){
        if (this.date.getDayOfWeek() == DayOfWeek.SATURDAY || this.date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            this.prix *= 1.20;
        }
        return this.prix;
    }

    public double calculeTarifReductionEstvip(){
        if (this.estVip) {
            this.prix *= 0.90;
        }
        return this.prix;
    }
    
    //le tarif dégressif de 15% 
     public double reductionTarif(String date,Stockage stockage) {
        int count = 0;

        for (Object[] r : stockage.getRendezVous()) {
            if (r[0].equals(this.patient) && r[2].equals(date)) {
                count++;
            }
        }

        if (count >= 2) {
            this.prix = this.prix - this.prix * 0.15;
        }
        return this.prix;
    }
}
