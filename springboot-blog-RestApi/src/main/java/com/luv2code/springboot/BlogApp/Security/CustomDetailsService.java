package com.luv2code.springboot.BlogApp.Security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.BlogApp.Dao.UserRepository;
import com.luv2code.springboot.BlogApp.Entity.Role;
import com.luv2code.springboot.BlogApp.Entity.User;

@Service
public class CustomDetailsService implements UserDetailsService{
	
	
	 private UserRepository userRepository;

	 
	   
    @Autowired
	public CustomDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException {
		
		 User user = userRepository.findByUsernameOrEmail(usernameoremail, usernameoremail)
	               .orElseThrow(() ->
	                       new UsernameNotFoundException("User not found with username or email:" + usernameoremail));
	        return new org.springframework.security.core.userdetails.User(user.getEmail(),
	                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	    }

	   

	 private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	    }
	
	

	
	
}
