package com.quintet.meditech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quintet.meditech.model.Users;

public interface UserJPARepository extends JpaRepository<Users, Integer> {

}
