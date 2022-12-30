package com.luv2code.springboot.BlogApp.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.BlogApp.Entity.Role;

public interface RoleRepository extends JpaRepository<Role , Long> {
	
	public Optional<Role> findByName(String name);

	
}
