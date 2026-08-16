package com.sunusante.tp1;

public class Specialite implements TypeConsultation {
    private String name="SPECIALISTE";
    @Override
    public double calculeTarifConsultation() {
        return 10000;
    }

    public String getName() {
        return name;
    }
}
