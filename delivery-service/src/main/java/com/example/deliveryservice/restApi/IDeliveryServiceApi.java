package com.example.deliveryservice.restApi;

import com.commons.delivery.DeliveryServiceStatus;
import com.commons.delivery.RestaurantOrderDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IDeliveryServiceApi {
	
	
	@PostMapping(value = "create")
	DeliveryServiceStatus acceptDelivery(@RequestBody RestaurantOrderDetails deliveryDetails);
	
}
