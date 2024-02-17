package com.tel.employeeservice.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
	
	@Autowired
	DiscoveryClient discoveryClient;
	
//	@Autowired
//	LoadBalancerClient loadBalancerClient;
//	
	@Override
	public EmployeeDTO getEmployee(int id) {
		AddressDTO addressDTO = restCallWithLoadBalancedAnnotation(id);
		Employee employee = employeeRepo.findById(id).get();
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		employeeDTO.setAddressDTO(addressDTO);
		return employeeDTO;
	}

	private AddressDTO restCallWithRestTemplet(int id) {
		return restTemplate.postForObject("http://localhost:8081/address-service/api/get-address/{id}", null, AddressDTO.class, id);
	}
	
	private AddressDTO restCallWithDiscoveryRestTemplet(int id) {
		
		List<ServiceInstance> instances = discoveryClient.getInstances("address-service");
		ServiceInstance serviceInstance = instances.get(0);
		String uri = serviceInstance.getUri().toString();
		String contextPath = serviceInstance.getMetadata().get("configPath");
		System.out.println(uri+contextPath);
		AddressDTO addressDTO = restTemplate.postForObject(uri+contextPath+"/get-address/{id}", null, AddressDTO.class, id);
		return addressDTO;
	}
	
//	private AddressDTO restCallWithLoadBalancedWay(int id) {
//		ServiceInstance serviceInstance = loadBalancerClient.choose("address-service");
//		String url = serviceInstance.getUri().toString();
//		String context = serviceInstance.getMetadata().get("configPath");
//		AddressDTO addressDTO = restTemplate.postForObject(url+context+"/get-address/{id}", null, AddressDTO.class, id);
//		return addressDTO;
//	}
	
	private AddressDTO restCallWithLoadBalancedAnnotation(int id) {
		
		AddressDTO addressDTO = restTemplate.postForObject("http://address-service/address-service/api/get-address/{id}", null, AddressDTO.class, id);
		return addressDTO;
	}

}
