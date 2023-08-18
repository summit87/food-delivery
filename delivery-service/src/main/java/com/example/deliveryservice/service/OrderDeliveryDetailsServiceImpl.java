package com.example.deliveryservice.service;

import com.example.deliveryservice.dao.OrderDeliveryDetailsDAO;
import com.example.deliveryservice.entity.OrderDeliveryDetails;
import org.springframework.stereotype.Service;

@Service
public class OrderDeliveryDetailsServiceImpl implements OrderDeliveryDetailsService {
  
  private final OrderDeliveryDetailsDAO orderDeliveryDetailsDAO;
  
  
  
  public OrderDeliveryDetailsServiceImpl(OrderDeliveryDetailsDAO orderDeliveryDetailsDAO) {
    this.orderDeliveryDetailsDAO = orderDeliveryDetailsDAO;
  }
  
  @Override
  public OrderDeliveryDetails saveOrderDetailsStatus(OrderDeliveryDetails orderDeliveryDetails) {
    return orderDeliveryDetailsDAO.save(orderDeliveryDetails);
  }
}
