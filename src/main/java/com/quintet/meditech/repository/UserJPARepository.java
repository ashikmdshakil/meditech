package com.quintet.meditech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.quintet.meditech.model.Users;

public interface UserJPARepository extends JpaRepository<Users, Integer> {
	public Users findByMobileNumber(String number);
	@Modifying
	@Transactional
	@Query(value = "update users set roles_role_id = :roleId where mobile_number = :mobileNumber ;", nativeQuery = true)
	public void updateUserRole(@Param("roleId") Integer roleId, @Param("mobileNumber") String mobileNumber);
	
}
