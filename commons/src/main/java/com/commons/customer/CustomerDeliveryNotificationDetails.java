package com.commons.customer;


import com.commons.model.DeliveryBoyDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CustomerDeliveryNotificationDetails {
	
	List<DeliveryBoyDetails> deliveryBoyDetails;
	CustomerDetails customerDetails;
	
	
}
