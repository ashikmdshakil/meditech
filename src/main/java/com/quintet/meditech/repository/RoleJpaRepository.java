package com.quintet.meditech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.quintet.meditech.model.Roles;

public interface RoleJpaRepository extends CrudRepository<Roles, Integer> {
	@Query(value = "select r.role_id, r.name, r.type from roles r;", nativeQuery = true)
	public List<Roles> findAll();
}
