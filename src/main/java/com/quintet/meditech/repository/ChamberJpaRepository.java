package com.quintet.meditech.repository;

import com.quintet.meditech.model.Chamber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamberJpaRepository extends JpaRepository<Chamber, Integer> {
    public Chamber findById(int id);
}
