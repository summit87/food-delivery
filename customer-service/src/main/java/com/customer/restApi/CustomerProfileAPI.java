package com.customer.restApi;


import com.customer.service.CustomerProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.customer.utils.Constants.RestApiConstants.CUSTOMER_PROFILE_MAPPING;

@RestController
@RequestMapping(name =CUSTOMER_PROFILE_MAPPING)
public class CustomerProfileAPI {

	private final CustomerProfileService customerProfileServiceImpl;
	
	public CustomerProfileAPI(CustomerProfileService customerProfileServiceImpl) {
		this.customerProfileServiceImpl = customerProfileServiceImpl;
	}






}
