package com.quintet.meditech.repository;

import com.quintet.meditech.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicineJpaRepository extends JpaRepository<Medicine, Integer> {
    @Query(value = "SELECT medicine_name from medicine;", nativeQuery = true)
    List<String> findAllMedicineNames();
    List<Medicine> findAll();
}
