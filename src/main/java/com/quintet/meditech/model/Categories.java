package com.quintet.meditech.model;

import org.hibernate.annotations.Fetch;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Component
@Entity
public class Categories {
    @Id
    private int id;
    private String name;
    @ManyToMany()
    private List<Users> users;

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

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
