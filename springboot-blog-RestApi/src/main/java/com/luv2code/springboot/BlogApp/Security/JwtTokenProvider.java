package com.luv2code.springboot.BlogApp.Security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.luv2code.springboot.BlogApp.CustomException.BlogApiExceptions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationMilliseconds;
	
	
	public String generateToken(Authentication authentication) {
		
		String username = authentication.getName();
		
		Date currentDate = new Date();
		
		Date expirationDate = new Date(currentDate.getTime() + jwtExpirationMilliseconds);
		
		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).
				setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		return token;
				
	}
	
	
	
	public String getUsernameFromToken(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		
		return claims.getSubject();
		
		
	}
	
	
	
	public boolean isExist(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			
			return true;
			
		}catch(Exception ex) {
			throw new BlogApiExceptions(HttpStatus.BAD_REQUEST , "Jwt Secret is invalid");
		}
		
		
		
		
	}
	
	
	
}
