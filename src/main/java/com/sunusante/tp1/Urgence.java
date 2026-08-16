package com.sunusante.tp1;

public class Urgence implements TypeConsultation {
    public String name="URGENCE";
    @Override
    public double calculeTarifConsultation() {
        return 15000;
    }

    public String getName() {
        return name;
    }
}
