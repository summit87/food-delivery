package com.customer.dao;

import com.customer.entity.CustomerProfileEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerProfileRepository extends
	ReactiveCrudRepository<CustomerProfileEntity, Integer> {
	
}