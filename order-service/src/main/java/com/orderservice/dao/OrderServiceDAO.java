package com.orderservice.dao;

import com.commons.enums.OrderStatusEnum;
import com.commons.enums.PaymentStatus;
import com.orderservice.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceDAO extends JpaRepository<OrderDetails, String> {
	@Modifying
	@Query("update OrderDetails o set o.paymentStatus =:toPaymentStatus , o.orderStatusEnum =:toOrderStatus where o.orderId = :orderId and o.paymentStatus =:fromPaymentStatus and o.orderStatusEnum =:fromOrderStatus")
	Integer updateOrderDetailsByOrderId(
			@Param("fromPaymentStatus") PaymentStatus fromPaymentStatus,
			@Param("toPaymentStatus") PaymentStatus toPaymentStatus,
			@Param("fromOrderStatus") OrderStatusEnum fromOrderStatus,
			@Param("toOrderStatus") OrderStatusEnum toOrderStatus,
			@Param("orderId") String orderId);
}
