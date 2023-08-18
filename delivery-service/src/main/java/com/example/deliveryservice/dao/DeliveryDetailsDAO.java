package com.example.deliveryservice.dao;

import com.commons.enums.DeliveryStatus;
import com.commons.enums.PaymentStatus;
import com.example.deliveryservice.entity.DeliveryDetails;
import com.example.deliveryservice.entity.DeliveryDetailsPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDetailsDAO extends JpaRepository<DeliveryDetails, DeliveryDetailsPk> {

  DeliveryDetails findDeliveryDetailsByPaymentStatus(PaymentStatus paymentStatus);

  DeliveryDetails findDeliveryDetailsByDeliveryStatus(DeliveryStatus deliveryStatus);

}
