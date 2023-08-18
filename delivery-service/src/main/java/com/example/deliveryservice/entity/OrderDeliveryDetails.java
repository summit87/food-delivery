package com.example.deliveryservice.entity;


import com.commons.enums.OrderStatusEnum;
import com.example.deliveryservice.constants.DatabaseConstants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = DatabaseConstants.ORDER_DELIVERY_DETAILS)
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDeliveryDetails extends AuditModel {
  
  @Id
  @Column(name = "order_delivery_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer orderDeliveryId;
  @Column(name = "delivery_id")
  private Integer deliveryId;
  @Column(name = "delivery_status")
  private OrderStatusEnum orderStatusEnum;
  
}
