package com.example.restaurantservice.dao;

import com.example.restaurantservice.entity.RestaurantAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, String> {

}
