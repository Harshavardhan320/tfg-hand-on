package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping(value="/company")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/welcome")
	public String Welcome() {
		return "Welcome to Employee Dashboard";
	}
	
	@PostMapping("/AddEmployee")
	public ResponseEntity<?> AddEmployee(@RequestBody Employee emp){
		
		try {
			Employee employee = this.employeeService.AddEmployee(emp);
			return new ResponseEntity<>(employee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	
	@GetMapping(value="/employees")
	public ResponseEntity<?> Employees(){
		try {
			List<Employee> employeeList = this.employeeService.getAllEmployee();
			return new ResponseEntity<>(employeeList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping(value="/employees/{id}")
	public ResponseEntity<?> Employees(@PathVariable long id){
		try {
			Optional<Employee> employeeList = this.employeeService.findById(id);
			return new ResponseEntity<>(employeeList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PatchMapping(value="/deleteEmploye/{id}")
	public ResponseEntity<?> deleteEmployees(@PathVariable long id){
		try {
			this.employeeService.deleteEmployeeById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
}
