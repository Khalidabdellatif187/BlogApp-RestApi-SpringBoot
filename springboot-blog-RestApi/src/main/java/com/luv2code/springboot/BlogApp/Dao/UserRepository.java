package com.luv2code.springboot.BlogApp.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.BlogApp.Entity.User;

public interface UserRepository extends JpaRepository<User , Long> {
	
	public Optional<User> findByUsernameOrEmail(String username , String email);
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByEmail(String email);
	
	public boolean existsByUsername(String username);
	
	public boolean existsByEmail(String email);
	
	

}
