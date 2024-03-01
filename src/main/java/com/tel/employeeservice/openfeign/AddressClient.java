package com.tel.employeeservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tel.employeeservice.dto.AddressDTO;

@FeignClient(name = "Address-Service",path = "/address-service/api")
public interface AddressClient {

	@PostMapping("/get-address/{id}")
	public AddressDTO getAddress(@PathVariable("id") int id);
	
	@PostMapping("/save-address")
	public String saveAddress(@RequestBody AddressDTO addressDTO);
}
