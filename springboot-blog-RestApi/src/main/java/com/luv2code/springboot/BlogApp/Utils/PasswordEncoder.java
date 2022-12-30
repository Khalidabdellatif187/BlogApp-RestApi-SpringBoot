package com.luv2code.springboot.BlogApp.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder passwordencoder = new BCryptPasswordEncoder();
		
		System.out.println(passwordencoder.encode("ahmed459"));
		
	     
		

	}

}
