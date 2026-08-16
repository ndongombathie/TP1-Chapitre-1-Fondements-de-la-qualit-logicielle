package com.sunusante.tp1;

public class Validation {
    private String patient;
    private String date;

    public Validation(String patient, String date){
        this.patient=patient;
        this.date=date;
    }

    public Validation(){}

    public  void patientNull(){
        if (this.patient == null || this.patient.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du patient est obligatoire");
        }
    }

    public  void dateNull(){
        if (this.date == null || this.date.trim().isEmpty()) {
            throw new IllegalArgumentException("La date est obligatoire");
        }
    }

}
