package com.customer.service;

import com.customer.entity.CustomerProfileEntity;
import reactor.core.publisher.Mono;

public interface CustomerProfileService {
	
	void saveCustomerProfile(CustomerProfileEntity customerProfileEntity);
	CustomerProfileEntity findCustomerProfileByEmailId(String emailId);
}
