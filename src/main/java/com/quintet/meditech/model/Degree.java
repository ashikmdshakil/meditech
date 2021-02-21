package com.quintet.meditech.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String degreeName;
    @OneToOne
    private Users user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
