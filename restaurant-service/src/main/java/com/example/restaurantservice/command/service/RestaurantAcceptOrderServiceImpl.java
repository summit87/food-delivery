package com.example.restaurantservice.command.service;

import com.commons.enums.DeliveryStatus;
import com.commons.enums.RestaurantOrderStatus;
import com.commons.utils.GenericBuilder;
import com.example.restaurantservice.command.RestaurantServiceCommandName;
import com.example.restaurantservice.dao.RestaurantOrderDAO;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.example.restaurantservice.model.RestaurantEventDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestaurantAcceptOrderServiceImpl implements RestaurantOrderService {

  private final RestaurantOrderDAO restaurantOrderDAO;

  public RestaurantAcceptOrderServiceImpl(RestaurantOrderDAO restaurantOrderDAO) {
    this.restaurantOrderDAO = restaurantOrderDAO;
  }

  @Override
  public RestaurantOrder acceptEventAndProcessMessage(RestaurantEventDetails restaurantEventDetails) {

    RestaurantOrder restaurantOrder =
        GenericBuilder.of(RestaurantOrder::new)
            .with(RestaurantOrder::setRestaurantId, restaurantEventDetails.getRestaurantId())
            .with(RestaurantOrder::setOrderId, restaurantEventDetails.getOrderId())
            .with(RestaurantOrder::setPaymentStatus, restaurantEventDetails.getPaymentStatus())
            .with(RestaurantOrder::setDeliveryStatus, DeliveryStatus.DELIVERY_PENDING)
            .with(
                RestaurantOrder::setRestaurantOrderStatus, RestaurantOrderStatus.ORDER_IN_PROGRESS)
            .with(RestaurantOrder::setCreatedBy, "system")
            .build();
    RestaurantOrder order = restaurantOrderDAO.save(restaurantOrder);
    log.info(
        "Restaurant order is in {} status for order id {} ",
        restaurantOrder.getRestaurantOrderStatus(),
        restaurantOrder.getOrderId());
    return order;
  }

  @Override
  public RestaurantOrder updateOrderServiceByOrderId(String orderId) {
    return null;
  }

	@Override
	public RestaurantServiceCommandName getCommandName() {
		return RestaurantServiceCommandName.RESTAURANT_ORDER_ACCEPT;
	}
}
