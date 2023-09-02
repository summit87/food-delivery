package com.customer.restApi;


import static com.customer.utils.Constants.RestApiConstants.CUSTOMER_PROFILE_MAPPING;

import com.customer.model.CustomerDetails;
import com.customer.service.CustomerProfileService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = CUSTOMER_PROFILE_MAPPING)
public class CustomerProfileAPI implements ICustomerProfileRestApi {
	
	private final CustomerProfileService customerProfileServiceImpl;
	
	public CustomerProfileAPI(CustomerProfileService customerProfileServiceImpl) {
		this.customerProfileServiceImpl = customerProfileServiceImpl;
	}
	
	
	@Override
	public void createUserProfile(CustomerDetails customerDetails, HttpHeaders headers)
		throws Exception {
		customerProfileServiceImpl.saveCustomerProfile(customerDetails, headers);
	}
	
	@Override
	public CustomerDetails getCustomerProfileByUserId(String userId) throws Exception {
		return customerProfileServiceImpl.findCustomerProfileByEmailId(userId);
	}
}
