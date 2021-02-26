package com.quintet.meditech.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
@Component
@Entity
public class Appoinment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime time;
    private String status;
    @ManyToOne
    private Users user;
    @ManyToOne
    private DoctorSlot doctorSlot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public DoctorSlot getDoctorSlot() {
        return doctorSlot;
    }

    public void setDoctorSlot(DoctorSlot doctorSlot) {
        this.doctorSlot = doctorSlot;
    }
}
