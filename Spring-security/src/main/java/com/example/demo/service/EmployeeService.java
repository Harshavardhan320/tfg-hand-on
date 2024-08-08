package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;

@Service
public class EmployeeService {

	
	@Autowired
	EmployeeRepo employeeRepo;
	
	
	public Employee AddEmployee(Employee emp) {
		
		return this.employeeRepo.save(emp);
	}
	
	public List<Employee> getAllEmployee(){
		
		return this.employeeRepo.findAll();
	}
	
	public Optional<Employee> findById(long id){
		return this.employeeRepo.findById(id);
	}
	
	public void deleteEmployeeById(long id) {
		this.employeeRepo.deleteById(id);
	}
	
}
