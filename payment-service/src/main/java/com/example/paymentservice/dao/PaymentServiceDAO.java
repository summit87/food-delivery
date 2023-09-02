package com.example.paymentservice.dao;

import com.example.paymentservice.entity.PaymentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentServiceDAO
	extends JpaRepository<PaymentStatusEntity, String> {
	
}
