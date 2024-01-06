package com.example.restaurantservice.dao;

import com.example.restaurantservice.entity.RestaurantProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantProfileRepository extends JpaRepository<RestaurantProfile, String> {

}
