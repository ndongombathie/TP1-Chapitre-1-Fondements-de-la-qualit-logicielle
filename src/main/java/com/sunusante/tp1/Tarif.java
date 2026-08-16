package com.sunusante.tp1;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Tarif {
    private String type;
    private LocalDate date;
    private boolean estVip;
    private double prix;

    public Tarif(){
        this.type = "";
        this.date = null;
        this.prix = 0.0;
    }

    public Tarif(String type,LocalDate date, double prix,boolean estVip){
        this.type=type;
        this.date=date;
        this.prix=prix;
        this.estVip=estVip;
    }

    public double calculeTarifTypeConsultation(){
        if (this.type.equals("GENERALISTE")) {
            this.prix = 5000;
        } else if (type.equals("SPECIALISTE")) {
            this.prix = 10000;
        } else if (type.equals("URGENCE")) {
            this.prix = 15000;
        } else {
            throw new IllegalArgumentException("Type de consultation inconnu: " + type);
        }
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
