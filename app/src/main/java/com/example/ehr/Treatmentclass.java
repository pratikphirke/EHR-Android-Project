package com.example.ehr;

public class Treatmentclass
    {

        String symptom;
        String disease;
        String []medicine;
        String []qty;


        public String getSymptom() {
            return symptom;
        }

        public void setSymptom(String symptom) {
            this.symptom = symptom;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public String[] getMedicine() {
            return medicine;
        }

        public void setMedicine(String[] medicine) {
            this.medicine = medicine;
        }

        public String[] getQty() {
            return qty;
        }

        public void setQty(String[] qty) {
            this.qty = qty;
        }


    }
