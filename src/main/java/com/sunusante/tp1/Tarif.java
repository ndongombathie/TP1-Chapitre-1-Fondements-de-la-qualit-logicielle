package com.sunusante.tp1;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Tarif {
    private TypeConsultation consultation ;
    private LocalDate date;
    private boolean estVip;
    private double prix;

    public Tarif(){
        this.consultation = null;
        this.date = null;
        this.prix = 0.0;
    }

    public Tarif(TypeConsultation type,LocalDate date, double prix,boolean estVip){
        this.consultation=type;
        this.date=date;
        this.prix=prix;
        this.estVip=estVip;
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
}
