package com.example.deliveryservice.service;

import com.commons.utils.GenericBuilder;
import com.example.deliveryservice.client.service.NotificationService;
import com.example.deliveryservice.dao.DeliveryDetailsDAO;
import com.example.deliveryservice.entity.DeliveryDetails;
import com.example.deliveryservice.entity.DeliveryOrderMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DeliveryDetailsImpl implements DeliveryService {
  
  private final DeliveryDetailsDAO deliveryDetailsDAO;
  private final DeliveryOrderMappingService deliveryOrderMappingServiceImpl;
  private final NotificationService notificationServiceImpl;
  
  public DeliveryDetailsImpl(DeliveryDetailsDAO deliveryDetailsDAO,
      DeliveryOrderMappingService deliveryOrderMappingServiceImpl,
      NotificationService notificationServiceImpl) {
    this.deliveryDetailsDAO = deliveryDetailsDAO;
    this.deliveryOrderMappingServiceImpl = deliveryOrderMappingServiceImpl;
    this.notificationServiceImpl = notificationServiceImpl;
  }
  
  @Override
  public void saveDelivery(DeliveryDetails deliveryDetails) {
    DeliveryDetails details = deliveryDetailsDAO.save(deliveryDetails);
    DeliveryOrderMapping deliveryOrderMapping = GenericBuilder.of(DeliveryOrderMapping::new)
        .with(DeliveryOrderMapping::setOrderId, details.getDeliveryDetailsPk().getOrderId())
        .with(DeliveryOrderMapping::setRestaurantId,
            details.getDeliveryDetailsPk().getRestaurantId())
        .build();
    DeliveryOrderMapping savedMapping = deliveryOrderMappingServiceImpl.save(deliveryOrderMapping);
    log.info("Delivery details are saved for order id {},restaurant id {} delivery id {} ",
        details.getDeliveryDetailsPk().getOrderId(),
        details.getDeliveryDetailsPk().getRestaurantId(), savedMapping.getDeliveryId());
    
  }
}
