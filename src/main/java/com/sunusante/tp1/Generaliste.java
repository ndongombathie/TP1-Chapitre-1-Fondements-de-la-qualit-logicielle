package com.sunusante.tp1;

public class Generaliste implements TypeConsultation {
    private String name ="GENERALISTE";
    @Override
    public double calculeTarifConsultation() {
        return 5000;
    }

    public String getName() {
        return name;
    }
}
