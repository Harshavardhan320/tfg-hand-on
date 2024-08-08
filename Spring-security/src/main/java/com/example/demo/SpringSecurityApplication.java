package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	PasswordEncoder encode;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		this.employeeRepo.save(new Employee("harsha",encode.encode("harsha"), Employee.Role.HR));
		this.employeeRepo.save(new Employee("ranadeep",encode.encode("ranadeep"),  Employee.Role.MANAGER));
		this.employeeRepo.save(new Employee("jai", encode.encode("jai"),Employee.Role.TL));
		this.employeeRepo.save(new Employee("dharani", encode.encode("dharani"), Employee.Role.TEAM));
		this.employeeRepo.save(new Employee("divya",encode.encode("divya"), Employee.Role.TEAM));
		this.employeeRepo.save(new Employee("sushma",encode.encode("sushma"), Employee.Role.TEAM));
		this.employeeRepo.save(new Employee("suma", encode.encode("suma"),Employee.Role.TEAM));
		this.employeeRepo.save(new Employee("vikatha",encode.encode("vikatha"), Employee.Role.TEAM));


		
	}

}
