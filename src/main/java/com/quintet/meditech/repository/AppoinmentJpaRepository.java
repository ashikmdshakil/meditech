package com.quintet.meditech.repository;

import com.quintet.meditech.model.Appoinment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppoinmentJpaRepository extends JpaRepository<Appoinment,Integer> {
}
