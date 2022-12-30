package com.luv2code.springboot.BlogApp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.luv2code.springboot.BlogApp.Security.CustomDetailsService;
import com.luv2code.springboot.BlogApp.Security.JwtAuthenticationEntryPoint;
import com.luv2code.springboot.BlogApp.Security.JwtAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
	
	
	  @Autowired
	  private CustomDetailsService userDetailsService;

	
	@Autowired
	private JwtAuthenticationEntryPoint authEntryPoint;
	
	
	@Bean
	public JwtAuthenticationFilter authFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	public PasswordEncoder passwordencoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(authEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
		authorizeRequests((authorize) -> authorize.antMatchers(HttpMethod.GET , "/api/**").permitAll()
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/swagger-ui.html/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.anyRequest().authenticated());
		
		
		http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordencoder());
    }
	
	
	
	@Bean
	public AuthenticationManager authenticationmanager(AuthenticationConfiguration auth) throws Exception{
		
		
		return auth.getAuthenticationManager();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
