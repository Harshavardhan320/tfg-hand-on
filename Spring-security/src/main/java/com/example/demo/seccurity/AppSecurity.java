package com.example.demo.seccurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.DataSecurityService;

@EnableWebSecurity
@Configuration
public class AppSecurity {
	
	@Autowired
	DataSecurityService dataSecurityService;

	@Bean
	UserDetailsService userDetailsServices() {
		return dataSecurityService;
	}
	
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	    	.csrf(AbstractHttpConfigurer::disable)
	        .authorizeHttpRequests((requests) -> requests
	        	.requestMatchers("/company/welcome").permitAll()
	            .requestMatchers("/company/employees" ,"/h2-console/**").hasRole("HR")
	            .requestMatchers("/company/employees/**").hasAnyRole("HR", "TEAM", "MANAGER")
	            .requestMatchers("/company/deleteEmploye/**").hasAnyRole("HR", "MANAGER")            
	        ).headers(headers->headers.frameOptions(frame->frame.disable()))
	        	
	        .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

	    return http.build();
	}
	
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider  provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(dataSecurityService);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
