package com.fooddelivery.authserver.restaurent.dao;

import com.fooddelivery.authserver.restaurent.entity.RestaurantAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, String> {

}
