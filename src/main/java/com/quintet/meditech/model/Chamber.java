package com.quintet.meditech.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
public class Chamber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String adress;
    @ManyToOne
    private Users user;
    @OneToMany(mappedBy = "chamber")
    @JsonIgnoreProperties("chamber")
    private List<DoctorSlot> doctorSlots;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<DoctorSlot> getDoctorSlots() {
        return doctorSlots;
    }

    public void setDoctorSlots(List<DoctorSlot> doctorSlots) {
        this.doctorSlots = doctorSlots;
    }
}
