package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;

@Service
public class DataSecurityService implements UserDetailsService {

	
	@Autowired
	EmployeeRepo employeeRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Employee> employee = this.employeeRepo.findByUsername(username);
		
		if(employee.isPresent()) {
			var emp = employee.get();
			
			return User.builder()
					.username(emp.getUsername())
					.password(emp.getPassword())
					.roles(getrole(emp))
					.build();
		}else {
			throw new UsernameNotFoundException(username);
		}
	}

	private String getrole(Employee emp) {
		if(emp.getRole() == null) { 
			return "USER";
		}else {
			return emp.getRole().toString();
		}
	}

}
