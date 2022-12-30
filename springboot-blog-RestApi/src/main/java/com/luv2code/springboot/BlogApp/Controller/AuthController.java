package com.luv2code.springboot.BlogApp.Controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.BlogApp.Dao.RoleRepository;
import com.luv2code.springboot.BlogApp.Dao.UserRepository;
import com.luv2code.springboot.BlogApp.Dto.JwtAuthResponse;
import com.luv2code.springboot.BlogApp.Dto.SignIn;
import com.luv2code.springboot.BlogApp.Dto.SignUp;
import com.luv2code.springboot.BlogApp.Entity.Role;
import com.luv2code.springboot.BlogApp.Entity.User;
import com.luv2code.springboot.BlogApp.Security.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="Auth Controller exposes Sign-in and Sign up Rest Apis")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
	private AuthenticationManager authenticationmanager;
	
	private UserRepository userrepo;
	
	private RoleRepository rolerepo;
	
	private PasswordEncoder passwordencoder;
	
	private JwtTokenProvider tokenProvider;

	@Autowired
	public AuthController(AuthenticationManager authenticationmanager, UserRepository userrepo, RoleRepository rolerepo,
			PasswordEncoder passwordencoder , JwtTokenProvider tokenProvider) {
		super();
		this.authenticationmanager = authenticationmanager;
		this.userrepo = userrepo;
		this.rolerepo = rolerepo;
		this.passwordencoder = passwordencoder;
		this.tokenProvider = tokenProvider;
	}
	
	@ApiOperation(value="Rest Api to sign in  or log in user to blog app")
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthResponse> signIn (@RequestBody SignIn signin) {
		
		Authentication authentication = authenticationmanager.authenticate
				(new UsernamePasswordAuthenticationToken(signin.getUsernameoremail() 
				, signin.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = tokenProvider.generateToken(authentication);
		
		JwtAuthResponse auth = new JwtAuthResponse(token);
		
		auth.setAccessToken(token);
		return  ResponseEntity.ok(auth);
		
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody SignUp signup) {
		
		
		if(userrepo.existsByUsername(signup.getUsername())) {
			return new ResponseEntity<>("This username is already Existed" , HttpStatus.BAD_REQUEST);
		} 
		if(userrepo.existsByEmail(signup.getEmail())) {
			return new ResponseEntity<>("This email is already Existed" , HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		
		user.setEmail(signup.getEmail());
		user.setName(signup.getName());
		user.setPassword(passwordencoder.encode(signup.getPassword()));
		user.setUsername(signup.getUsername());
		
		Role roles = rolerepo.findByName("ADMIN").get();
		
		user.setRoles(Collections.singleton(roles));
		
		
		userrepo.save(user);
		
		return new ResponseEntity<>("You registered Succcessfully" , HttpStatus.OK);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
