package com.quintet.meditech.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

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
    private int appoinmentId;
    private String advice;
    @ManyToOne
    private Users patient;
    @OneToOne
    private Users doctor;
    @ManyToMany
    private List<Medicine> medicines;
    @ManyToMany
    private List<Test> tests;
    @OneToMany(mappedBy = "prescription")
    @JsonIgnoreProperties("prescription")
    private List<MedicineScedule> scedules;
    @OneToOne
    private Users referredDoctor;

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

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public int getAppoinmentId() {
        return appoinmentId;
    }

    public void setAppoinmentId(int appoinmentId) {
        this.appoinmentId = appoinmentId;
    }

    public List<MedicineScedule> getScedules() {
        return scedules;
    }

    public void setScedules(List<MedicineScedule> scedules) {
        this.scedules = scedules;
    }

    public Users getReferredDoctor() {
        return referredDoctor;
    }

    public void setReferredDoctor(Users referredDoctor) {
        this.referredDoctor = referredDoctor;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
