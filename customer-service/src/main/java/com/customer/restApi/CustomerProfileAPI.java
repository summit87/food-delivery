package com.customer.restApi;


import com.customer.service.CustomerProfileService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerProfileAPI {
	
	private final CustomerProfileService customerProfileServiceImpl;
	
	public CustomerProfileAPI(CustomerProfileService customerProfileServiceImpl) {
		this.customerProfileServiceImpl = customerProfileServiceImpl;
	}
	
	///public Mono<Void> createCustomerProfile(@RequestBody Custo)
}
