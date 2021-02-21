package com.quintet.meditech.repository;

import com.quintet.meditech.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriesJpaRepository extends JpaRepository<Categories, Integer> {
    public Categories findById(int id);
}
