package com.quintet.meditech.repository;

import com.quintet.meditech.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestJpaRepository extends JpaRepository<Test, Integer> {
}
