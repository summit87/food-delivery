package com.customer.service;

import com.customer.model.CustomerDetails;
import org.springframework.http.HttpHeaders;

public interface CustomerProfileService {
	
	void saveCustomerProfile(CustomerDetails customerDetails, HttpHeaders headers) throws Exception;
	
	CustomerDetails findCustomerProfileByEmailId(String emailId) throws Exception;
}
