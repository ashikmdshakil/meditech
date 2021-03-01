package com.quintet.meditech.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String medicine;
    private String test;
    private String doctorName;
    private String referredTo;
    @ManyToOne
    private Users patient;
    @OneToOne
    private Users doctor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getReferredTo() {
        return referredTo;
    }

    public void setReferredTo(String referredTo) {
        this.referredTo = referredTo;
    }

    public Users getPatient() {
        return patient;
    }

    public void setPatient(Users patient) {
        this.patient = patient;
    }

    public Users getDoctor() {
        return doctor;
    }

    public void setDoctor(Users doctor) {
        this.doctor = doctor;
    }
}
