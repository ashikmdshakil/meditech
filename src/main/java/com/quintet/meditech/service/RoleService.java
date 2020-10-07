package com.quintet.meditech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quintet.meditech.model.Roles;
import com.quintet.meditech.repository.RoleJpaRepository;

@Service
public class RoleService {
	@Autowired
	private RoleJpaRepository roleRepo;
	
	public List<Roles> getSystemRoles(){
		return roleRepo.findAll();
	}
}
