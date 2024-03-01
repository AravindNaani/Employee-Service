package com.tel.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tel.employeeservice.dto.EmployeeDTO;
import com.tel.employeeservice.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/get-employee/{id}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable int id){
	
		EmployeeDTO employeeDTO = employeeService.getEmployee(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
	}
	
	@PostMapping("/save-employee")
	public String saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		
		employeeService.saveEmployee(employeeDTO);
		return "Data Saved";
	}

}
