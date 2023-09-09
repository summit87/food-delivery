package com.example.deliveryservice.restApi;


import com.commons.delivery.DeliveryServiceStatus;
import com.commons.delivery.RestaurantOrderDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delivery")
public class DeliveryServiceApi implements IDeliveryServiceApi {
	
	@Override
	public DeliveryServiceStatus acceptDelivery(RestaurantOrderDetails deliveryDetails) {
		return null;
	}
}
