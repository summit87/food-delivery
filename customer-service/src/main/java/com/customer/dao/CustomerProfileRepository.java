package com.customer.dao;

import com.customer.entity.CustomerProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerProfileRepository extends
	JpaRepository<CustomerProfileEntity, String> {
	
}