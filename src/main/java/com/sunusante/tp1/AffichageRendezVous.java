package com.sunusante.tp1;

import java.util.List;


public class AffichageRendezVous {

 public AffichageRendezVous(){

 }
 
 public void afficherRendezVous(List<Object[]> stockage) {
        for (Object[] r : stockage) {
            System.out.println(r[0] + " | " + r[1] + " | " + r[2] + " | VIP=" + r[3] + " | " + r[4] + " FCFA");
        }
    }   
}
