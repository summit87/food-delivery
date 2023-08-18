package com.example.deliveryservice.service;

import com.example.deliveryservice.dao.DeliveryOrderMappingDAO;
import com.example.deliveryservice.entity.DeliveryOrderMapping;
import org.springframework.stereotype.Service;

@Service
public class DeliveryOrderMappingServiceImpl implements DeliveryOrderMappingService {
	
	private final DeliveryOrderMappingDAO deliveryOrderMappingDAO;
	
	public DeliveryOrderMappingServiceImpl(DeliveryOrderMappingDAO deliveryOrderMappingDAO) {
		this.deliveryOrderMappingDAO = deliveryOrderMappingDAO;
	}
	
	@Override
	public DeliveryOrderMapping save(DeliveryOrderMapping deliveryOrderMapping) {
		return deliveryOrderMappingDAO.save(deliveryOrderMapping);
	}
}
