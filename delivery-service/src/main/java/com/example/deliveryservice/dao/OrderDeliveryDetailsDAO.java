package com.example.deliveryservice.dao;

import com.example.deliveryservice.entity.OrderDeliveryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDeliveryDetailsDAO extends JpaRepository<OrderDeliveryDetails,Integer> {

}
