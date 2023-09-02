package com.example.restaurantservice.dao;

import com.commons.enums.RestaurantOrderStatus;
import com.example.restaurantservice.entity.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOrderDAO
	extends JpaRepository<RestaurantOrder, String> {
	
	@Modifying
	@Query("update RestaurantOrder ro set ro.restaurantOrderStatus =:toRestaurantStatus where ro.restaurantId =:restaurantId and ro.orderId =:orderId and ro.restaurantOrderStatus =:fromRestaurantStatus")
	Integer updateRestaurantOrderByOrderIdAndRestaurantId(
		@Param("toRestaurantStatus") RestaurantOrderStatus toRestaurantStatus,
		@Param("orderId") String orderId,
		@Param("restaurantId") String restaurantId,
		@Param("fromRestaurantStatus") RestaurantOrderStatus fromRestaurantStatus);
}
