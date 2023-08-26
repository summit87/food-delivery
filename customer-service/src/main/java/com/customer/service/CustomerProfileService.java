package com.customer.service;

import com.customer.entity.CustomerProfileEntity;
import com.customer.model.CustomerDetails;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

public interface CustomerProfileService {
	
	void saveCustomerProfile(CustomerDetails customerDetails, HttpHeaders headers) throws Exception;
	CustomerProfileEntity findCustomerProfileByEmailId(String emailId)throws Exception;
}
