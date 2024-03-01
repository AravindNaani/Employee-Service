package com.tel.employeeservice.service;

import com.tel.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {
	
	EmployeeDTO getEmployee(int id);

	void saveEmployee(EmployeeDTO employeeDTO);

}
