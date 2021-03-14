package com.quintet.meditech.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Entity
public class DoctorSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int fees;
    private int maximumNumberOfAppoinment;
    @ManyToOne
    private Users user;
    @OneToMany(mappedBy = "doctorSlot")
    @JsonIgnore
    private List<Appoinment> appoinments;
    @ManyToOne
    private Chamber chamber;
    private String dayName;
    private int weekToRepeat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getMaximumNumberOfAppoinment() {
        return maximumNumberOfAppoinment;
    }

    public void setMaximumNumberOfAppoinment(int maximumNumberOfAppoinment) {
        this.maximumNumberOfAppoinment = maximumNumberOfAppoinment;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Appoinment> getAppoinments() {
        return appoinments;
    }

    public void setAppoinments(List<Appoinment> appoinments) {
        this.appoinments = appoinments;
    }

    public Chamber getChamber() {
        return chamber;
    }

    public void setChamber(Chamber chamber) {
        this.chamber = chamber;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getWeekToRepeat() {
        return weekToRepeat;
    }

    public void setWeekToRepeat(int weekToRepeat) {
        this.weekToRepeat = weekToRepeat;
    }
}
