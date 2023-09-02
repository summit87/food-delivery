package com.customer.restApi;

import static com.customer.utils.Constants.RestApiConstants.CREATE_CUSTOMER_PROFILE;

import com.customer.model.CustomerDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


public interface ICustomerProfileRestApi {
	
	@PostMapping(path = CREATE_CUSTOMER_PROFILE)
	void createUserProfile(@RequestBody CustomerDetails customerDetails,
		@RequestHeader HttpHeaders headers) throws Exception;
	
	@GetMapping(path = "/{userId}")
	CustomerDetails getCustomerProfileByUserId(@PathVariable("userId") String userId)
		throws Exception;
	
}
