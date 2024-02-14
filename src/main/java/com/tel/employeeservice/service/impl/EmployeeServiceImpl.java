package com.tel.employeeservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tel.employeeservice.dao.EmployeeRepo;
import com.tel.employeeservice.dto.AddressDTO;
import com.tel.employeeservice.dto.EmployeeDTO;
import com.tel.employeeservice.entity.Employee;
import com.tel.employeeservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public EmployeeDTO getEmployee(int id) {
		
		AddressDTO addressDTO = restTemplate.postForObject("http://localhost:8081/address-service/api/get-address/{id}", null, AddressDTO.class, id);
		Employee employee = employeeRepo.findById(id).get();
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		employeeDTO.setAddressDTO(addressDTO);
		return employeeDTO;
	}

}
