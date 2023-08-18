package com.example.deliveryservice.dao;

import com.example.deliveryservice.entity.DeliveryOrderMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOrderMappingDAO extends JpaRepository<DeliveryOrderMapping,Integer> {

}
