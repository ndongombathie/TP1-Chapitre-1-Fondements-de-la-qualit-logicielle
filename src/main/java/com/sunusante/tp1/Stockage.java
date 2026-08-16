package com.sunusante.tp1;

import java.util.ArrayList;
import java.util.List;

public class Stockage {

    private final List<Object[]> rendezVous = new ArrayList<>();

    public void save(String patient,TypeConsultation consultation,String date,boolean estVip,double prix) {

        rendezVous.add(new Object[]{patient,consultation,date,estVip,prix});
    }

    public List<Object[]> getRendezVous() {
        return rendezVous;
    }
}
